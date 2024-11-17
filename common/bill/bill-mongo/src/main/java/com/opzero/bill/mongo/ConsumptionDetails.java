package com.opzero.bill.mongo;

import lombok.Data;

@Data
public class ConsumptionDetails {
    private double presentReading;
    private double previousReading;
    private double consumption;
}
