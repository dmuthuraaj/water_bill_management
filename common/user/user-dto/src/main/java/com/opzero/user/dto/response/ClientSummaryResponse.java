package com.opzero.user.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ClientSummaryResponse {

    private String id;
    private String name;
    private String meterSerialNumber;
    private String category;
    private double previousReading;
    private LocalDateTime createdAt;
    private boolean active;
}
