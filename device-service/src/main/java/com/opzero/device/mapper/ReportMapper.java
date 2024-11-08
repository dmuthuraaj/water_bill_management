package com.opzero.device.mapper;

import com.opzero.device.dto.response.ReportDetailResponse;
import com.opzero.device.dto.response.ReportSummaryResponse;
import com.opzero.device.mongo.entity.Device;
import com.opzero.device.mongo.entity.MeterReading;

public class ReportMapper {

    public static ReportSummaryResponse toSummaryResponse(Device device) {
        ReportSummaryResponse response = new ReportSummaryResponse();
        response.setId(device.getId());
        response.setSerialNumber(device.getSerialNumber());
        response.setClientId(device.getClientId());
        response.setConsumed(device.getTotalLitresConsumed());
        return response;
    }

    public static ReportDetailResponse toDetailResponse(MeterReading meterReading) {
        ReportDetailResponse response = new ReportDetailResponse();
        response.setDeviceId(meterReading.getDeviceId());
        response.setSerialNumber(meterReading.getSerialNumber());
        response.setMonth(meterReading.getMonth().toString());
        response.setConsumed(meterReading.getMeterReading());
        return response;
    }
}
