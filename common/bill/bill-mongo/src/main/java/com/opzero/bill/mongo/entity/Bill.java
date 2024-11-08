package com.opzero.bill.mongo.entity;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import com.opzero.core.dto.BillStatus;
import com.opzero.core.mongo.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection = "bills")
@EqualsAndHashCode(callSuper = true)
public class Bill extends BaseEntity{

    private String clientId;
    private LocalDate readingDate;
    private LocalDate dueDate;
    private double rate;
    private double totalBill;
    private double previousReading;
    private double currentReading;
    private BillStatus status;
}