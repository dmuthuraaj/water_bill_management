package com.opzero.device.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DeviceDetailResponse {
    private String serialNumber;
    private String id;
    private String deviceEUI;
    private String clientId;
    private double totalLitresConsumed;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
