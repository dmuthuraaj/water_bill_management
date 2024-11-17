package com.opzero.bill.dto.response;

import java.time.LocalDate;

import com.opzero.core.dto.BillStatus;

import lombok.Data;

@Data
public class BillSummaryResponse {

    private String id;
    private String clientId;
    private LocalDate readingDate;
    private LocalDate dueDate;
    private double totalAmount;
    private BillStatus status;
}
