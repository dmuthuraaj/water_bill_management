package com.opzero.user.dto.request;

import lombok.Data;

@Data
public class ClientCreateRequest {

    private String category;
    private String name;
    private String mobileNumber;
    private String address;
    private String deviceId;
    private String meterSerialNumber;
    private Double firstReading;
}
