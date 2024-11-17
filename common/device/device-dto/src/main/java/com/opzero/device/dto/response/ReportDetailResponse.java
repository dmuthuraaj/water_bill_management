package com.opzero.device.dto.response;

import lombok.Data;

@Data
public class ReportDetailResponse {
    private String deviceId;
    private String serialNumber;
    private double consumed;
    private String hhuId;
//    private double pending;
    private String month;
}
