package com.opzero.device.service.grpc;

import java.util.Optional;

import org.lognet.springboot.grpc.GRpcService;

import com.opzero.core.exception.ExceptionCodes;
import com.opzero.device.grpc.CommonResponse;
import com.opzero.device.grpc.DeviceByIdRequest;
import com.opzero.device.grpc.DeviceInfo;
import com.opzero.device.grpc.DeviceResponse;
import com.opzero.device.grpc.DeviceServiceGrpc;
import com.opzero.device.mongo.entity.Device;
import com.opzero.device.mongo.repository.DeviceRepository;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@GRpcService
@AllArgsConstructor
public class GrpcDeviceService extends DeviceServiceGrpc.DeviceServiceImplBase {
        private final DeviceRepository deviceRepository;

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
                                        .setCode(ExceptionCodes.RESOURCE_NOT_FOUND_EXCEPTION)
                                        .setInUse(device.isInUse())
                                        .setIsActive(device.isActive())
                                        .setIsDeleted(device.isDeleted())
                                        .setIsError(false)
                                        .setMessage("SUCCESS")
                                        .build();
                        DeviceInfo deviceInfo = DeviceInfo.newBuilder()
                                        .setId(device.getId())
                                        .setSerialNumber(device.getSerialNumber())
                                        .setClientId(device.getClientId())
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
}
