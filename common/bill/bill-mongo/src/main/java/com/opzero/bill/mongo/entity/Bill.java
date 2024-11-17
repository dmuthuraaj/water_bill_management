package com.opzero.bill.mongo.entity;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import com.opzero.bill.mongo.BillingDetails;
import com.opzero.bill.mongo.ConsumerDetails;
import com.opzero.bill.mongo.ConsumptionDetails;
import com.opzero.bill.mongo.PreviousMonthPayment;
import com.opzero.core.dto.BillStatus;
import com.opzero.core.mongo.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection = "bills")
@EqualsAndHashCode(callSuper = true)
public class Bill extends BaseEntity{

    private String clientId;
    private BillStatus status;
    private long billNumber;
    private LocalDate readingDate;
    private LocalDate dueDate;
    private ConsumerDetails consumerDetails;
    private ConsumptionDetails consumptionDetails;
    private BillingDetails billingDetails;
    private PreviousMonthPayment previousMonthPayment;
}