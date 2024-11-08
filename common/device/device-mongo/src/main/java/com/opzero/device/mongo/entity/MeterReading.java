package com.opzero.device.mongo.entity;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.data.mongodb.core.mapping.Document;

import com.opzero.core.mongo.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection = "report")
@EqualsAndHashCode(callSuper = true)
public class MeterReading extends BaseEntity {
    // private String deviceEUI;
    private String deviceId;
    private String serialNumber;
    private double meterReading;
    private LocalDate date;
    private Month month;
    private String hhuId;
    // @DBRef
    // private Device device;
}
