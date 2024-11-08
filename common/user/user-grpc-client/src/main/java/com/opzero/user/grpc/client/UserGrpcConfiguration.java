package com.opzero.user.grpc.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.opzero.user.grpc.UserServiceGrpc;

import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

@Configuration
public class UserGrpcConfiguration {

    @Value("${grpc.user.port}")
    private int port;

    @Value("${grpc.user.host}")
    private String host;

    @Bean
    public UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub() {
        return UserServiceGrpc.newBlockingStub(NettyChannelBuilder.forAddress(host, port).usePlaintext().build());
    }
}