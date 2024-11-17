package com.opzero.device.dto.response;

import lombok.Data;

@Data
public class ReportSummaryResponse {
    private String id;
    private String serialNumber;
    private String month;
    private double consumed;
}
