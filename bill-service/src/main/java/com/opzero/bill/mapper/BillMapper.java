package com.opzero.bill.mapper;

import org.springframework.beans.BeanUtils;

import com.opzero.bill.dto.response.BillDetailResponse;
import com.opzero.bill.dto.response.BillSummaryResponse;
import com.opzero.bill.mongo.entity.Bill;

public class BillMapper {

    public static BillSummaryResponse toSummaryResponse(Bill bill) {
        BillSummaryResponse response = new BillSummaryResponse();
        BeanUtils.copyProperties(bill, response);
        return response;
    }

    public static BillDetailResponse toDetailResponse(Bill bill) {
        BillDetailResponse response = new BillDetailResponse();
        BeanUtils.copyProperties(bill, response);
        return response;
    }
}
