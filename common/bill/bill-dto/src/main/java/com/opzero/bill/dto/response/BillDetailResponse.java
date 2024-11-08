package com.opzero.bill.dto.response;

import java.time.LocalDate;

import com.opzero.core.dto.BillStatus;

import lombok.Data;

@Data
public class BillDetailResponse {

    private String id;
    private String clientId;
    private LocalDate readingDate;
    private LocalDate dueDate;
    private double rate;
    private double totalBill;
    private double previousReading;
    private double currentReading;
    private BillStatus status;
}
