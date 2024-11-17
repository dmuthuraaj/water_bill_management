package com.opzero.device.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DeviceSummaryResponse {

    private String id;
    private String serialNumber;
    private String hhuId;
    private double totalLitresConsumed;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
