package com.opzero.device.mapper;

import com.opzero.device.dto.response.DeviceDetailResponse;
import com.opzero.device.dto.response.DeviceSummaryResponse;
import com.opzero.device.mongo.entity.Device;

public class DeviceMapper {

    public static DeviceSummaryResponse toResponse(Device device) {
        DeviceSummaryResponse response = new DeviceSummaryResponse();
        response.setId(device.getId());
        response.setSerialNumber(device.getSerialNumber());
        response.setHhuId(device.getHhuId());
        response.setTotalLitresConsumed(device.getTotalLitresConsumed());
        response.setActive(device.isActive());
        response.setCreatedAt(device.getCreatedAt());
        response.setUpdatedAt(device.getModifiedAt());
        return response;
    }

    public static DeviceDetailResponse toDetailResponse(Device device) {
        DeviceDetailResponse response = new DeviceDetailResponse();
        response.setId(device.getId());
        response.setDeviceEUI(device.getDeviceEUI());
        response.setSerialNumber(device.getSerialNumber());
        response.setTotalLitresConsumed(device.getTotalLitresConsumed());
        response.setActive(device.isActive());
        response.setCreatedAt(device.getCreatedAt());
        response.setUpdatedAt(device.getModifiedAt());
        return response;
    }

}
