package com.opzero.bill.mongo;

import lombok.Data;

@Data
public class BillingDetails {
    private double waterCharges;
    private double meterCharges;
    private double sanitaryCharges;
    // private double S.C forBorewell;
    private double otherCharges;
    private double arrears;
    private double interest;
    private double totalAmount;
    private double advanceAmount;
    private double netAmountDue;
}
