package com.opzero.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.opzero.core.exception.ResourceNotExistException;
import com.opzero.user.dto.request.ClientCategoryCreateRequest;
import com.opzero.user.dto.request.ClientCategoryUpdateRequest;
import com.opzero.user.dto.response.ClientCategoryResponse;
import com.opzero.user.mapper.ClientCategoryMapper;
import com.opzero.user.mongo.entity.ClientCategory;
import com.opzero.user.mongo.repository.ClientCategoryRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ClientCategoryService {

    private final ClientCategoryRepository clientCategoryRepository;

    public boolean create(ClientCategoryCreateRequest request) {
        return true;
    }

    public List<ClientCategoryResponse> getAll() {
        List<ClientCategory> clientCategories = clientCategoryRepository.findAll();
        return clientCategories.stream().map(ClientCategoryMapper::toResponse).collect(Collectors.toList());
    }

    public ClientCategoryResponse get(String id) {
        Optional<ClientCategory> optionalClientCategory = clientCategoryRepository.findById(id);
        if (optionalClientCategory.isEmpty()) {
            throw new ResourceNotExistException("Client category does not exist with id " + id);
        }
        return ClientCategoryMapper.toResponse(optionalClientCategory.get());
    }

    public boolean update(String id, ClientCategoryUpdateRequest request) {
        Optional<ClientCategory> optionalClientCategory = clientCategoryRepository.findById(id);
        if (optionalClientCategory.isEmpty()) {
            throw new ResourceNotExistException("Client category does not exist with id " + id);
        }
        ClientCategory clientCategory = optionalClientCategory.get();
        clientCategory.setName(request.getName());
        clientCategory.setActive(request.isActive());
        clientCategoryRepository.save(clientCategory);
        return true;
    }

    public boolean delete(String id) {
        Optional<ClientCategory> optionalClientCategory = clientCategoryRepository.findById(id);
        if (optionalClientCategory.isEmpty()) {
            throw new ResourceNotExistException("Client category does not exist with id " + id);
        }
        ClientCategory clientCategory = optionalClientCategory.get();
        clientCategory.setDeleted(true);
        clientCategoryRepository.save(clientCategory);
        return true;
    }
}
