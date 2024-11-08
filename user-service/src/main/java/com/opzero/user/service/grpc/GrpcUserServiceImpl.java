package com.opzero.user.service.grpc;

import org.lognet.springboot.grpc.GRpcService;

import com.opzero.user.grpc.GrpcCreateClientRequest;
import com.opzero.user.grpc.GrpcCreateClientResponse;
import com.opzero.user.grpc.ProtoGetProfileRequest;
import com.opzero.user.grpc.ProtoGetProfileResponse;
import com.opzero.user.grpc.UserServiceGrpc;
import com.opzero.user.mongo.entity.Client;
import com.opzero.user.mongo.repository.ClientRepository;
import com.opzero.user.mongo.repository.UserRepository;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@GRpcService
@AllArgsConstructor
public class GrpcUserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepository userRepository;

    private final ClientRepository clientRepository;

    @Override
    public void createClient(GrpcCreateClientRequest request,
            StreamObserver<GrpcCreateClientResponse> responseObserver) {
        if (clientRepository.findOneByMobileNumber(request.getMobileNumber()).isPresent()) {
            GrpcCreateClientResponse response = GrpcCreateClientResponse.newBuilder().setIsError(true)
                    .setError("client already exists").build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
        Client client = new Client();
        client.setMobileNumber(request.getMobileNumber());
        client.setName(request.getName());
        client.setCategory(request.getCategory());
        client.setMeterSerialNumber(request.getMeterSerialNumber());
        client.setPreviousReading(request.getFirstReading());
        client.setAddress(request.getAddress());
        clientRepository.save(client);

        GrpcCreateClientResponse response = GrpcCreateClientResponse.newBuilder().setIsError(false)
                .setClientId(client.getId()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getProfile(ProtoGetProfileRequest request, StreamObserver<ProtoGetProfileResponse> responseObserver) {
    }
}
