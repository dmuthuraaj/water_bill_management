package com.opzero.device.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opzero.core.dto.CommonResponse;
import com.opzero.device.dto.response.ReportDetailResponse;
import com.opzero.device.dto.response.UploadReportResponse;
import com.opzero.device.service.ReportService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/report")
@AllArgsConstructor
public class ReportApi {

        private final ReportService reportService;

        @PostMapping("/upload/v1")
        public ResponseEntity<CommonResponse<List<UploadReportResponse>>> upload(
                        @RequestParam("csvFile") MultipartFile csvFile) {
                return ResponseEntity
                                .ok(new CommonResponse<>(reportService.uploadReport(csvFile),
                                                "Report uploaded successfully", 200));
        }

        @PostMapping("/upload")
        public ResponseEntity<CommonResponse<List<UploadReportResponse>>> uploadMonthly(
                        @RequestParam("csvFile") MultipartFile csvFile) {
                return ResponseEntity
                                .ok(new CommonResponse<>(reportService.monthlyReportUpload(csvFile),
                                                "Report uploaded successfully",
                                                200));
        }

        @GetMapping("/{id}")
        public ResponseEntity<CommonResponse<List<ReportDetailResponse>>> getAllDeviceReport(
                        @PathVariable(name = "id") String id) {
                return ResponseEntity
                                .ok(new CommonResponse<>(reportService.getAllDeviceReport(id),
                                                "Device report listed successfully", 200));
        }
}
