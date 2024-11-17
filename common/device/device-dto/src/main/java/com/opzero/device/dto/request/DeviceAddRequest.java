package com.opzero.device.dto.request;

import lombok.Data;

@Data
public class DeviceAddRequest {
    private String hhuId;
    private String serialNumber;
    // Probably not needed
    private String deviceId;
    private String clientId;
}
