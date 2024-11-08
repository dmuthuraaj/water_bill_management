package com.opzero.device.dto.request;

import lombok.Data;

@Data
public class DeviceAddRequest {
    private String deviceId;
    private String hhuId;
    private String serialNumber;
    private String clientId;
}
