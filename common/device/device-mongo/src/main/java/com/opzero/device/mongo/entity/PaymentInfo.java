package com.opzero.device.mongo.entity;

import lombok.Data;

@Data
public class PaymentInfo {
    private double totalLitres;
    private double paidLitres;
    private double pendingLitres;
}
