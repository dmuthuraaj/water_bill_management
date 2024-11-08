package com.opzero.device.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opzero.core.dto.CommonResponse;
import com.opzero.device.dto.request.DeviceAddRequest;
import com.opzero.device.dto.response.DeviceDetailResponse;
import com.opzero.device.dto.response.DeviceSummaryResponse;
import com.opzero.device.service.DeviceService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class DeviceApi {
    private final DeviceService deviceService;

    @PostMapping("/add")
    public ResponseEntity<CommonResponse<Boolean>> addDevice(@RequestBody DeviceAddRequest request) {
        return ResponseEntity
                .ok(new CommonResponse<>(deviceService.addDevice(request), "Device added successfully", 200));
    }

    @PostMapping("/add/devices")
    public ResponseEntity<CommonResponse<Boolean>> addDevices(@RequestParam("csvFile") MultipartFile csvFile) {
        return ResponseEntity
                .ok(new CommonResponse<>(deviceService.addDevices(csvFile), "Device added successfully", 200));
    }

    @GetMapping("/all")
    public ResponseEntity<CommonResponse<List<DeviceSummaryResponse>>> getAllDevices() {
        return ResponseEntity
                .ok(new CommonResponse<>(deviceService.getAllDevices(), "Device listed successfully", 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<DeviceDetailResponse>> getDeviceById(@PathVariable(name = "id") String id) {
        return ResponseEntity
                .ok(new CommonResponse<>(deviceService.getDeviceById(id), "Device Details listed successfully", 200));
    }
}
