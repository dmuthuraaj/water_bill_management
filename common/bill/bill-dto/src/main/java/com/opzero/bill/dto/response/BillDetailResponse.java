package com.opzero.bill.dto.response;

import java.time.LocalDate;

import com.opzero.bill.mongo.BillingDetails;
import com.opzero.bill.mongo.ConsumerDetails;
import com.opzero.bill.mongo.ConsumptionDetails;
import com.opzero.bill.mongo.PreviousMonthPayment;
import com.opzero.core.dto.BillStatus;

import lombok.Data;

@Data
public class BillDetailResponse {

    private String id;
    private String clientId;
    private BillStatus status;
    private long billNumber;
    private LocalDate readingDate;
    private LocalDate dueDate;

    private ConsumerDetails consumerDetails;
    // private String name;
    // private String address;
    // private String subDivision;
    // private String rrNumber;
    // private String consumerNumber;

    private ConsumptionDetails consumptionDetails;
    // private double presentReading;
    // private double previousReading;
    // private double consumption;

    private BillingDetails billingDetails;
    // private double waterCharges;
    // private double meterCharges;
    // private double sanitaryCharges;
    // private double S.C forBorewell;
    // private double otherCharges;
    // private double arrears;
    // private double interest;
    // private double totalAmount;
    // private double advanceAmount;
    // private double netAmountDue;

    private PreviousMonthPayment previousMonthPayment;
    // private String receiptNumber;
    // private LocalDate payDate;
    // private double amount;
    // private String mode;
    // private String paidAt;
}
