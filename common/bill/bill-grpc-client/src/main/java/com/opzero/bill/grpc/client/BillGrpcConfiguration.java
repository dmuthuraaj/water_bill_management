package com.opzero.bill.grpc.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.opzero.bill.grpc.BillServiceGrpc;

import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

@Configuration
public class BillGrpcConfiguration {

    @Value("${grpc.bill.port}")
    private int port;

    @Value("${grpc.bill.host}")
    private String host;

    @Bean
    public BillServiceGrpc.BillServiceBlockingStub billingServiceBlockingStub() {
        return BillServiceGrpc.newBlockingStub(NettyChannelBuilder.forAddress(host, port).usePlaintext().build());
    }
}