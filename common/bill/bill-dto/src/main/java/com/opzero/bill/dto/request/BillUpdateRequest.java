package com.opzero.bill.dto.request;

import com.opzero.core.dto.BillStatus;

import lombok.Data;

@Data
public class BillUpdateRequest {

    private BillStatus status;
}
