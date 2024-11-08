package com.opzero.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.opzero.core.exception.DuplicateResourceException;
import com.opzero.core.exception.ResourceNotExistException;
import com.opzero.user.dto.request.ClientCreateRequest;
import com.opzero.user.dto.request.ClientUpdateRequest;
import com.opzero.user.dto.response.ClientDetailResponse;
import com.opzero.user.dto.response.ClientSummaryResponse;
import com.opzero.user.mapper.ClientMapper;
import com.opzero.user.mongo.entity.Client;
import com.opzero.user.mongo.repository.ClientRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public boolean create(ClientCreateRequest request) {
        Optional<Client> optionalClient = clientRepository.findOneByMobileNumber(request.getMobileNumber());
        if (optionalClient.isPresent()) {
            throw new DuplicateResourceException("Client already exist with mobile number");
        }
        Client client = new Client();
        client.setCategory(request.getCategory());
        client.setName(request.getName());
        client.setMeterSerialNumber(request.getMeterSerialNumber());
        // todo: update the first reading to the device using GRPC
        client.setPreviousReading(request.getFirstReading());
        client.setAddress(request.getAddress());
        client.setMobileNumber(request.getMobileNumber());
        client.setActive(true);
        clientRepository.save(client);
        return true;
    }

    public List<ClientSummaryResponse> getAll() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(ClientMapper::toSummaryResponse).collect(Collectors.toList());
    }

    public ClientDetailResponse getById(String id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new ResourceNotExistException("Client does not exist");
        }
        return ClientMapper.toDetailResponse(optionalClient.get());
    }

    public boolean update(String id, ClientUpdateRequest request) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new ResourceNotExistException("Client does not exist");
        }
        Client client = optionalClient.get();
        client.setName(request.getName());
        client.setAddress(request.getAddress());
        client.setMobileNumber(request.getMobileNumber());
        client.setMeterSerialNumber(request.getMeterSerialNumber());
        client.setCategory(request.getCategory());
        clientRepository.save(client);
        return true;
    }

    public boolean delete(String id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new ResourceNotExistException("Client does not exist");
        }
        Client client = optionalClient.get();
        client.setDeleted(true);
        clientRepository.save(client);
        return true;
    }
}
