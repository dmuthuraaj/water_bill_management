package com.opzero.user.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ClientDetailResponse {

    private String id;
    private String name;
    // private String code;
    private String mobileNumber;
    private String Address;
    private String meterSerialNumber;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean active;
    private boolean delete;
}
