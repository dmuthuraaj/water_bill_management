package com.opzero.device.grpc.client;

import com.opzero.device.grpc.DeviceServiceGrpc;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceGrpcConfiguration {
    @Value("${grpc.device.port}")
    private int port;

    @Value("${grpc.device.host}")
    private String host;


    @Bean
    public DeviceServiceGrpc.DeviceServiceBlockingStub deviceServiceBlockingStub() {
        return DeviceServiceGrpc.newBlockingStub(NettyChannelBuilder.forAddress(host, port).usePlaintext().build());
    }
}