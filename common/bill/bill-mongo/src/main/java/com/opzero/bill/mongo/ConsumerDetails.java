package com.opzero.bill.mongo;

import lombok.Data;

@Data
public class ConsumerDetails {
    private String name;
    private String address;
    private String subDivision;
    private String rrNumber;
    private String consumerNumber;
}
