package com.opzero.bill.mapper;

import org.springframework.beans.BeanUtils;

import com.opzero.bill.dto.response.BillDetailResponse;
import com.opzero.bill.dto.response.BillSummaryResponse;
import com.opzero.bill.mongo.entity.Bill;

public class BillMapper {

    public static BillSummaryResponse toSummaryResponse(Bill bill) {
        BillSummaryResponse response = new BillSummaryResponse();
        response.setId(bill.getId());
        response.setClientId(bill.getClientId());
        response.setReadingDate(bill.getReadingDate());
        response.setDueDate(bill.getDueDate());
        response.setTotalAmount(bill.getBillingDetails().getTotalAmount());
        response.setStatus(bill.getStatus());
        return response;
    }

    public static BillDetailResponse toDetailResponse(Bill bill) {
        BillDetailResponse response = new BillDetailResponse();
        BeanUtils.copyProperties(bill, response);
        // response.setId(bill.getId());
        // response.setClientId(bill.getClientId());
        // response.setReadingDate(bill.getReadingDate());
        // response.setDueDate(bill.getDueDate());
        // response.setStatus(bill.getStatus());

        // private ConsumerDetails consumerDetails;
        // response.setName(bill.getConsumerDetails().getName());
        // response.setAddress(bill.getConsumerDetails().getAddress());
        // response.setSubDivision(bill.getConsumerDetails().getSubDivision());
        // response.setRrNumber(bill.getConsumerDetails().getRrNumber());
        // response.setConsumerNumber(bill.getConsumerDetails().getConsumerNumber());

        // private ConsumptionDetails consumptionDetails;
        // response.setPresentReading(bill.getConsumptionDetails().getPresentReading());
        // response.setPreviousReading(bill.getConsumptionDetails().getPreviousReading());
        // response.setConsumption(bill.getConsumptionDetails().getConsumption());

        // private BillingDetails billingDetails;
        // response.setWaterCharges(bill.getBillingDetails().getWaterCharges());
        // response.setMeterCharges(bill.getBillingDetails().getMeterCharges());
        // response.setSanitaryCharges(bill.getBillingDetails().getSanitaryCharges());
        // private double S.C forBorewell;
        // response.setOtherCharges(bill.getBillingDetails().getOtherCharges());
        // response.setArrears(bill.getBillingDetails().getArrears());
        // response.setInterest(bill.getBillingDetails().getInterest());
        // response.setTotalAmount(bill.getBillingDetails().getTotalAmount());
        // response.setAdvanceAmount(bill.getBillingDetails().getAdvanceAmount());
        // response.setNetAmountDue(bill.getBillingDetails().getNetAmountDue());

        // private PreviousMonthPayment previousMonthPayment;
        // response.setReceiptNumber(bill.getPreviousMonthPayment().getReceiptNumber());
        // response.setPayDate(bill.getPreviousMonthPayment().getPayDate());
        // response.setAmount(bill.getPreviousMonthPayment().getAmount());
        // response.setMode(bill.getPreviousMonthPayment().getMode());
        // response.setPaidAt(bill.getPreviousMonthPayment().getPaidAt());

        return response;
    }
}
