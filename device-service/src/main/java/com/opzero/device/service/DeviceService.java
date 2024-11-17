package com.opzero.device.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opzero.core.exception.BadRequestException;
import com.opzero.core.exception.DuplicateResourceException;
import com.opzero.core.exception.ResourceNotExistException;
import com.opzero.core.util.Random;
import com.opzero.device.dto.request.DeviceAddRequest;
import com.opzero.device.dto.response.DeviceDetailResponse;
import com.opzero.device.dto.response.DeviceSummaryResponse;
import com.opzero.device.mapper.DeviceMapper;
import com.opzero.device.mongo.entity.Device;
import com.opzero.device.mongo.repository.DeviceRepository;
import com.opzero.user.grpc.GrpcCreateClientRequest;
import com.opzero.user.grpc.GrpcCreateClientResponse;
import com.opzero.user.grpc.UserServiceGrpc;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    private final UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    public boolean addDevice(DeviceAddRequest request) {
        Optional<Device> optionalDevice = deviceRepository.findBySerialNumberAndHhuId(request.getSerialNumber(),
                request.getHhuId());
        if (optionalDevice.isPresent()) {
            throw new DuplicateResourceException("Device with serial number already exist");
        }
        Device device = new Device();
        device.setSerialNumber(request.getSerialNumber());
        // device.setDeviceEUI(request.getDeviceId());
        // device.setClientId(request.getClientId());
        device.setHhuId(request.getHhuId());
        deviceRepository.save(device);
        return true;
    }

    private static boolean fileNameCheck(String name) {
        String fileNamePattern = "^HHU+_[0-9]+\\.csv$";
        return name != null && name.matches(fileNamePattern);
    }

    private String extractFileId(String fileName) {
        return fileName.substring(fileName.lastIndexOf('_') + 1, fileName.length() - 4);
    }

    public boolean addDevices(MultipartFile csvFile) {
        String fileName = csvFile.getOriginalFilename();
        if (!fileNameCheck(fileName)) {
            throw new BadRequestException("File name should in the format of HHU_{id}.csv");
        }
        String hhuId = extractFileId(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                String devEUI = csvRecord.get("DevEUI");
                String meterSerial = csvRecord.get("meter_serial").replace("\r", "").replace("\n", "");
                Optional<Device> optionalDevice = deviceRepository.findBySerialNumberAndHhuId(meterSerial, hhuId);
                if (optionalDevice.isPresent()) {
                    continue;
                }
                Device device = new Device();
                device.setDeviceEUI(devEUI);
                device.setSerialNumber(meterSerial);
                device.setHhuId(hhuId);
                GrpcCreateClientResponse clientResponse = userServiceBlockingStub
                        .createClient(GrpcCreateClientRequest.newBuilder()
                                .setCategory("RESIDENTIAL")
                                .setName(Random.random(6, true, false))
                                .setMobileNumber(Random.random(10, false, true))
                                .setMeterSerialNumber(meterSerial)
                                .setFirstReading(0)
                                .setAddress(Random.random(4, true, false) + " Colony").build());
                if (clientResponse.getIsError()) {
                    log.info("client not created for device serial {}", meterSerial);
                }
                deviceRepository.save(device);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Bad Request");
        }
        return true;
    }

    public List<DeviceSummaryResponse> getAllDevices() {
        List<Device> devices = deviceRepository.findAll();
        if (devices.isEmpty()) {
            throw new ResourceNotExistException("Device list empty");
        }
        return devices.stream().map(DeviceMapper::toResponse).collect(Collectors.toList());
    }

    public DeviceDetailResponse getDeviceById(String id) {
        Optional<Device> optionalDevice = deviceRepository.findById(id);
        if (optionalDevice.isEmpty()) {
            throw new ResourceNotExistException("Device is not exist with id " + id);
        }
        return DeviceMapper.toDetailResponse(optionalDevice.get());
    }
}
