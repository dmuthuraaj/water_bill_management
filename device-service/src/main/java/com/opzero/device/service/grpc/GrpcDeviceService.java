package com.opzero.device.service.grpc;

import java.time.Month;
import java.time.ZoneId;
import java.util.Optional;
import java.time.LocalDateTime;

import org.lognet.springboot.grpc.GRpcService;

import com.opzero.core.exception.ExceptionCodes;
import com.opzero.device.grpc.CommonResponse;
import com.opzero.device.grpc.DeviceByIdRequest;
import com.opzero.device.grpc.DeviceBySerialNumber;
import com.opzero.device.grpc.DeviceInfo;
import com.opzero.device.grpc.DeviceResponse;
import com.opzero.device.grpc.DeviceServiceGrpc;
import com.opzero.device.grpc.GetReportRequest;
import com.opzero.device.grpc.GetReportResponse;
import com.opzero.device.mongo.entity.Device;
import com.opzero.device.mongo.entity.MeterReading;
import com.opzero.device.mongo.repository.DeviceRepository;
import com.opzero.device.mongo.repository.MeterReadingRepository;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@GRpcService
@AllArgsConstructor
public class GrpcDeviceService extends DeviceServiceGrpc.DeviceServiceImplBase {
        private final DeviceRepository deviceRepository;

        private final MeterReadingRepository meterReadingRepository;

        @Override
        public void getDeviceDetail(DeviceByIdRequest request,
                        StreamObserver<DeviceResponse> responseObserver) {
                log.info("GetDeviceDetail request: {}", request);
                Optional<Device> byId = deviceRepository.findById(request.getId());
                if (byId.isEmpty()) {
                        CommonResponse commonResponse = CommonResponse.newBuilder()
                                        .setCode(ExceptionCodes.RESOURCE_NOT_FOUND_EXCEPTION)
                                        .setInUse(false)
                                        .setIsActive(false)
                                        .setIsDeleted(false)
                                        .setIsError(true)
                                        .setMessage("Device not found for request Id")
                                        .build();
                        DeviceResponse deviceResponse = DeviceResponse.newBuilder()
                                        .setCommon(commonResponse).build();

                        responseObserver.onNext(deviceResponse);
                        responseObserver.onCompleted();
                } else {
                        Device device = byId.get();
                        CommonResponse commonResponse = CommonResponse.newBuilder()
                                        .setCode(200)
                                        .setInUse(device.isInUse())
                                        .setIsActive(device.isActive())
                                        .setIsDeleted(device.isDeleted())
                                        .setIsError(false)
                                        .setMessage("SUCCESS")
                                        .build();
                        DeviceInfo deviceInfo = DeviceInfo.newBuilder()
                                        .setId(device.getId())
                                        .setSerialNumber(device.getSerialNumber())
                                        // .setClientId(device.getClientId())
                                        .setDeviceEUI(device.getDeviceEUI())
                                        .setTotalLitresConsumed(device.getTotalLitresConsumed())
                                        .build();
                        DeviceResponse response = DeviceResponse.newBuilder()
                                        .setCommon(commonResponse)
                                        .setInfo(deviceInfo)
                                        .build();

                        responseObserver.onNext(response);
                        responseObserver.onCompleted();
                }
        }

        @Override
        public void getDeviceBySerialNumber(DeviceBySerialNumber request,
                        StreamObserver<DeviceResponse> responseObserver) {
                log.info("GetDeviceBySerialNumber request: {}", request);
                Optional<Device> byId = deviceRepository.findOneBySerialNumber(request.getSerialNumber());
                if (byId.isEmpty()) {
                        CommonResponse commonResponse = CommonResponse.newBuilder()
                                        .setCode(ExceptionCodes.RESOURCE_NOT_FOUND_EXCEPTION)
                                        .setInUse(false)
                                        .setIsActive(false)
                                        .setIsDeleted(false)
                                        .setIsError(true)
                                        .setMessage("Device not found for request Id")
                                        .build();
                        DeviceResponse deviceResponse = DeviceResponse.newBuilder()
                                        .setCommon(commonResponse).build();

                        responseObserver.onNext(deviceResponse);
                        responseObserver.onCompleted();
                } else {
                        Device device = byId.get();
                        CommonResponse commonResponse = CommonResponse.newBuilder()
                                        .setCode(200)
                                        .setInUse(device.isInUse())
                                        .setIsActive(device.isActive())
                                        .setIsDeleted(device.isDeleted())
                                        .setIsError(false)
                                        .setMessage("SUCCESS")
                                        .build();
                        DeviceInfo.Builder deviceInfoBuilder = DeviceInfo.newBuilder();
                                deviceInfoBuilder.setId(device.getId());
                                deviceInfoBuilder.setSerialNumber(device.getSerialNumber());
                                // if(device.getClientId()!=null){
                                //         deviceInfoBuilder.setClientId(device.getClientId());
                                // }
                                if(device.getDeviceEUI()!=null){
                                        deviceInfoBuilder.setDeviceEUI(device.getDeviceEUI());
                                }
                                deviceInfoBuilder.setTotalLitresConsumed(device.getTotalLitresConsumed());
                                        
                        DeviceResponse.Builder responseBuilder = DeviceResponse.newBuilder();
                                responseBuilder.setCommon(commonResponse);
                                responseBuilder.setInfo(deviceInfoBuilder.build());

                        responseObserver.onNext(responseBuilder.build());
                        responseObserver.onCompleted();
                }
        }

        @Override
        public void getReport(GetReportRequest request, StreamObserver<GetReportResponse> responseObserver){
                log.info("GetReport request: {}", request);
                Month month = Month.valueOf(request.getMonth().toUpperCase());
                log.info("Month {}",month);
                Optional<MeterReading> optionalMeterReading = meterReadingRepository.findByDeviceIdAndMonth(request.getDeviceId(), month);
                if(optionalMeterReading.isEmpty()){
                        CommonResponse commonResponse = CommonResponse.newBuilder()
                        .setCode(ExceptionCodes.RESOURCE_NOT_FOUND_EXCEPTION)
                        .setInUse(false)
                        .setIsActive(false)
                        .setIsDeleted(false)
                        .setIsError(true)
                        .setMessage("Report not found for request")
                        .build();

                        GetReportResponse reportResponse = GetReportResponse.newBuilder()
                                .setCommon(commonResponse).build();
                        responseObserver.onNext(reportResponse);
                        responseObserver.onCompleted();
                }else{
                        MeterReading report = optionalMeterReading.get();
                        CommonResponse commonResponse = CommonResponse.newBuilder()
                                        .setCode(200)
                                        .setInUse(report.isInUse())
                                        .setIsActive(report.isActive())
                                        .setIsDeleted(report.isDeleted())
                                        .setIsError(false)
                                        .setMessage("SUCCESS")
                                        .build();

                        GetReportResponse response = GetReportResponse.newBuilder()
                                .setCommon(commonResponse)
                                .setId(report.getId())
                                .setTotalLitresConsumed(report.getMeterReading())
                                .setReadingDate(report.getCreatedAt().atZone(ZoneId.systemDefault()).toEpochSecond())
                                .build();
                        responseObserver.onNext(response);
                        responseObserver.onCompleted(); 
                }
        }
}
