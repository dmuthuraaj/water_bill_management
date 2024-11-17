package com.opzero.user.dto.request;

import lombok.Data;

@Data
public class ClientUpdateRequest {

    private String name;
    private String mobileNumber;
    private String Address;
    private String meterSerialNumber;
    private String category;
}
