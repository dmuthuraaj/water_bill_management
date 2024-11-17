package com.opzero.bill.dto.request;

import lombok.Data;

@Data
public class BillAddRequest {
    private String clientId;
    private String month;
}
