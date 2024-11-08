package com.opzero.bill.dto.request;

import java.time.LocalDate;

import com.opzero.core.dto.BillStatus;

import lombok.Data;

@Data
public class BillAddRequest {
    private String clientId;
    private LocalDate readingDate;
    private LocalDate dueDate;
    private double rate;
    private double totalBill;
    private double previousReading;
    private double currentReading;
    private BillStatus status;
}
