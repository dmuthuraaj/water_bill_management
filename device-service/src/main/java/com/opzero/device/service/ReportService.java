package com.opzero.device.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opzero.core.exception.BadRequestException;
import com.opzero.core.exception.ResourceNotExistException;
import com.opzero.device.dto.response.ReportDetailResponse;
import com.opzero.device.dto.response.UploadReportResponse;
import com.opzero.device.mapper.ReportMapper;
import com.opzero.device.mongo.PaymentInfo;
import com.opzero.device.mongo.entity.Device;
import com.opzero.device.mongo.entity.MeterReading;
import com.opzero.device.mongo.repository.DeviceRepository;
import com.opzero.device.mongo.repository.MeterReadingRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ReportService {
    private final DeviceRepository deviceRepository;
    private final MeterReadingRepository meterReadingRepository;

    public List<MeterReading> getAllReport(String id) {
        List<MeterReading> meterReadings = meterReadingRepository.findByDeviceId(id);
        if (meterReadings == null) {
            throw new ResourceNotExistException("No report available");
        }
        return meterReadings;
    }

    private static boolean fileNameCheck(String name) {
        // String fileNamePattern = "^HHU+_[0-9]_+\\.csv$";
        String fileNamePattern = "^HHU+_[0-9]+_(January|February|March|April|May|June|July|August|September|October|November|December|Jan|Feb|Mar|Apr|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\.csv$";
        return name != null && name.matches(fileNamePattern);
    }

    private String extractFileId(String fileName) {
        return fileName.substring(fileName.indexOf('_') + 1, fileName.lastIndexOf('_'));
    }

    private String extractMonth(String fileName) {
        return fileName.substring(fileName.lastIndexOf('_') + 1, fileName.length() - 4);
    }

    public Month monthConversion(String abbr) {
        return Arrays.stream(Month.values())
                .filter(month -> month.name().substring(0, 3).equalsIgnoreCase(abbr.substring(0, 3)))
                .findFirst().orElseThrow(() -> new BadRequestException("Provided month value is not correct"));
    }

    public List<UploadReportResponse> monthlyReportUpload(MultipartFile csvFile) {
        String fileName = csvFile.getOriginalFilename();
        if (!fileNameCheck(fileName)) {
            throw new BadRequestException("File name should in the format of HHU_{id}_{month}.csv");
        }
        String hhuId = extractFileId(fileName);
        Month month = monthConversion(extractMonth(fileName));
        LocalDate date = LocalDate.of(Year.now().getValue(), month, 1);
        List<UploadReportResponse> responses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                UploadReportResponse response = new UploadReportResponse();
                String devEUI = csvRecord.get("dev_id");
                // String meterSerial = csvRecord.get("meter_serial").replace("\r",
                // "").replace("\n", "");
                String meterSerial = csvRecord.get("meter_serial").trim();
                String timestamp = csvRecord.get("pdu_timestamp").replace("-", "");
                String reading = csvRecord.get("meterreading").replace("-", "0");
                String clusterLabel = csvRecord.get("Cluster_lable");
                response.setSerialNumber(meterSerial);
                Optional<Device> optionalDevice = deviceRepository.findBySerialNumberAndHhuId(meterSerial, hhuId);
                if (optionalDevice.isEmpty()) {
                    response.setResult("Device with serial number not available");
                    responses.add(response);
                    continue;
                }
                Device device = optionalDevice.get();
                double totalLitres = device.getTotalLitresConsumed() + Double.parseDouble(reading);

                Optional<MeterReading> optionalMeterReading = meterReadingRepository
                        .findBySerialNumberAndMonthAndHhuId(meterSerial, date.getMonth(), hhuId);
                if (optionalMeterReading.isEmpty()) {
                    MeterReading meterReading = new MeterReading();
                    meterReading.setSerialNumber(device.getSerialNumber());
                    meterReading.setDeviceId(device.getId());
                    meterReading.setHhuId(device.getHhuId());
                    meterReading.setMeterReading(Double.parseDouble(reading));
                    meterReading.setDate(date);
                    meterReading.setMonth(month);
                    meterReadingRepository.save(meterReading);

                    response.setResult("Upload Successful");
                    responses.add(response);
                }
                device.setTotalLitresConsumed(totalLitres);
                deviceRepository.save(device);
            }
            return responses;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Bad Request");
        }
    }

    public List<ReportDetailResponse> getAllDeviceReport(String id) {
        List<MeterReading> reading = meterReadingRepository.findByDeviceId(id);
        if (reading.isEmpty()) {
            throw new ResourceNotExistException("Device Report list empty");
        }
        return reading.stream().map(ReportMapper::toDetailResponse).collect(Collectors.toList());
    }

    public List<UploadReportResponse> uploadReport(MultipartFile csvFile) {
        String fileName = csvFile.getOriginalFilename();
        if (!fileNameCheck(fileName)) {
            throw new BadRequestException("File name should in the format of HHU_{id}_{month}.csv");
        }
        String hhuId = extractFileId(fileName);
        Month month = monthConversion(extractMonth(fileName));
        LocalDate date = LocalDate.of(Year.now().getValue(), month, 1);
        List<UploadReportResponse> responses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                UploadReportResponse response = new UploadReportResponse();
                String devEUI = csvRecord.get("dev_id");
                // String meterSerial = csvRecord.get("meter_serial").replace("\r",
                // "").replace("\n", "");
                String meterSerial = csvRecord.get("meter_serial").trim();
                String timestamp = csvRecord.get("pdu_timestamp").replace("-", "");
                String reading = csvRecord.get("meterreading").replace("-", "0");
                String clusterLabel = csvRecord.get("Cluster_lable");
                response.setSerialNumber(meterSerial);
                Optional<Device> optionalDevice = deviceRepository.findBySerialNumberAndHhuId(meterSerial, hhuId);
                if (optionalDevice.isEmpty()) {
                    response.setResult("Device with serial number not available");
                    responses.add(response);
                    continue;
                }
                Device device = optionalDevice.get();
                double totalLitres = device.getTotalLitresConsumed() + Double.parseDouble(reading);

                Optional<MeterReading> optionalMeterReading = meterReadingRepository
                        .findBySerialNumberAndMonthAndHhuId(meterSerial, date.getMonth(), hhuId);
                if (optionalMeterReading.isEmpty()) {
                    MeterReading meterReading = new MeterReading();
                    meterReading.setSerialNumber(device.getSerialNumber());
                    meterReading.setDeviceId(device.getId());
                    meterReading.setHhuId(device.getHhuId());
                    meterReading.setMeterReading(Double.parseDouble(reading));
                    meterReading.setDate(date);
                    meterReading.setMonth(month);
                    meterReadingRepository.save(meterReading);

                    PaymentInfo paymentInfo = new PaymentInfo();
                    paymentInfo.setTotalLitres(totalLitres);
                    device.setTotalLitresConsumed(totalLitres);
                    deviceRepository.save(device);
                    response.setResult("Upload Successful");
                    responses.add(response);
                }
            }
            return responses;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Bad Request");
        }
    }

    // public List<UploadReportResponse> uploadReport(MultipartFile csvFile) {
    //     List<UploadReportResponse> responses = new ArrayList<>();
    //     try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()));
    //             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
    //         for (CSVRecord csvRecord : csvParser) {
    //             UploadReportResponse response = new UploadReportResponse();
    //             String devEUI = csvRecord.get("DevEUI");
    //             String meterSerial = csvRecord.get("meter_serial").replace("\r", "").replace("\n", "");
    //             String year = csvRecord.get("Timestamp");
    //             String reading = "";
    //             response.setSerialNumber(meterSerial);
    //             Optional<Device> optionalDevice = deviceRepository.findBySerialNumber(meterSerial);
    //             if (optionalDevice.isEmpty()) {
    //                 response.setResult("Device with serial number not available");
    //                 responses.add(response);
    //                 continue;
    //             }
    //             Device device = optionalDevice.get();
    //             device.setDeviceEUI(devEUI);
    //             device.setSerialNumber(meterSerial);
    //             double totalLitres = 0;
    //             for (Month month : Month.values()) {
    //                 String monthName = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    //                 reading = csvRecord.get(monthName);

    //                 MeterReading meterReading = new MeterReading();
    //                 // meterReading.setDevice(device);
    //                 meterReading.setSerialNumber(device.getSerialNumber());
    //                 meterReading.setMeterReading(Double.parseDouble(reading));
    //                 LocalDate date = LocalDate.of(Integer.parseInt(year), month, 1);
    //                 meterReading.setDate(date);
    //                 // find data by device and date
    //                 Optional<MeterReading> optionalMeterReading = meterReadingRepository
    //                         .findBySerialNumberAndDate(device.getSerialNumber(), date);
    //                 if (optionalMeterReading.isPresent()) {
    //                     continue;
    //                 }
    //                 meterReadingRepository.save(meterReading);

    //                 PaymentInfo paymentInfo = new PaymentInfo();
    //                 totalLitres += Double.parseDouble(reading);
    //                 paymentInfo.setTotalLitres(totalLitres);
    //                 device.setTotalLitresConsumed(totalLitres);
    //                 deviceRepository.save(device);
    //             }
    //             response.setResult("Upload Successful");
    //             responses.add(response);
    //             // SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy
    //             // HH:mm:ss");
    //             // SimpleDateFormat simpleformat = new SimpleDateFormat("MMMM");
    //             // String strMonth = simpleformat.format(new Date());
    //             // log.info("Got Report for device id: {}", devEUI);
    //         }
    //         return responses;
    //     } catch (IOException e) {
    //         log.error(e.getMessage(), e);
    //         throw new RuntimeException("Bad Request");
    //     }
    // }
}
