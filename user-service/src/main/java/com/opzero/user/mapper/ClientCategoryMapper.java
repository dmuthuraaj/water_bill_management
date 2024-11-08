package com.opzero.user.mapper;

import org.springframework.beans.BeanUtils;

import com.opzero.user.dto.response.ClientCategoryResponse;
import com.opzero.user.mongo.entity.ClientCategory;

public class ClientCategoryMapper {
    public static ClientCategoryResponse toResponse(ClientCategory clientCategory) {
        ClientCategoryResponse response = new ClientCategoryResponse();
        BeanUtils.copyProperties(clientCategory, response);
        return response;
    }
}
