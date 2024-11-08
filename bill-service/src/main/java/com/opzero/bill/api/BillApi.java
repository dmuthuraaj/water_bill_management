package com.opzero.bill.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opzero.bill.dto.request.BillAddRequest;
import com.opzero.bill.dto.request.BillUpdateRequest;
import com.opzero.bill.dto.response.BillDetailResponse;
import com.opzero.bill.dto.response.BillSummaryResponse;
import com.opzero.bill.service.BillService;
import com.opzero.core.dto.CommonResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class BillApi {
    private final BillService billService;

    @PostMapping("/add")
    public ResponseEntity<CommonResponse<Boolean>> addBill(@RequestBody BillAddRequest request) {
        return ResponseEntity
                .ok(new CommonResponse<>(billService.add(request), "Bill added successfully", 200));
    }

    @GetMapping("/all")
    public ResponseEntity<CommonResponse<List<BillSummaryResponse>>> getAll() {
        return ResponseEntity
                .ok(new CommonResponse<>(billService.getAll(), "Bills listed successfully", 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<BillDetailResponse>> getById(@PathVariable(name = "id") String id) {
        return ResponseEntity
                .ok(new CommonResponse<>(billService.getById(id), "Bill details listed successfully", 200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<Boolean>> update(@PathVariable(name = "id") String id,
            @RequestBody BillUpdateRequest request) {
        return ResponseEntity
                .ok(new CommonResponse<>(billService.update(id, request), "Bill details updated successfully", 200));
    }
}
