package com.opzero.user.mapper;

import org.springframework.beans.BeanUtils;

import com.opzero.user.dto.response.ClientDetailResponse;
import com.opzero.user.dto.response.ClientSummaryResponse;
import com.opzero.user.mongo.entity.Client;

public class ClientMapper {

    public static ClientSummaryResponse toSummaryResponse(Client client) {
        ClientSummaryResponse response = new ClientSummaryResponse();
        BeanUtils.copyProperties(client, response);
        return response;
    }

    public static ClientDetailResponse toDetailResponse(Client client) {
        ClientDetailResponse response = new ClientDetailResponse();
        BeanUtils.copyProperties(client, response);
        return response;
    }
}
