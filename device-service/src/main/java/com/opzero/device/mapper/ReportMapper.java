package com.opzero.device.mapper;

import com.opzero.device.dto.response.ReportDetailResponse;
import com.opzero.device.dto.response.ReportSummaryResponse;
import com.opzero.device.mongo.entity.MeterReading;

public class ReportMapper {

    public static ReportSummaryResponse toSummaryResponse(MeterReading meterReading) {
        ReportSummaryResponse response = new ReportSummaryResponse();
        response.setId(meterReading.getId());
        response.setSerialNumber(meterReading.getSerialNumber());
        response.setMonth(meterReading.getMonth().toString());
        response.setConsumed(meterReading.getMeterReading());
        return response;
    }

    public static ReportDetailResponse toDetailResponse(MeterReading meterReading) {
        ReportDetailResponse response = new ReportDetailResponse();
        response.setDeviceId(meterReading.getDeviceId());
        response.setHhuId(meterReading.getHhuId());
        response.setSerialNumber(meterReading.getSerialNumber());
        response.setMonth(meterReading.getMonth().toString());
        response.setConsumed(meterReading.getMeterReading());
        return response;
    }
}
