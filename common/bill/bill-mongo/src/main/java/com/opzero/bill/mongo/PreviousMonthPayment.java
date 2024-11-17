package com.opzero.bill.mongo;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PreviousMonthPayment {
    private String receiptNumber;
    private LocalDate payDate;
    private double amount;
    private String mode;
    private String paidAt;
}
