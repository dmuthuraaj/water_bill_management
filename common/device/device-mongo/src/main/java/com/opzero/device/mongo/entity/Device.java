package com.opzero.device.mongo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.opzero.core.mongo.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection = "devices")
@EqualsAndHashCode(callSuper = true)
public class Device extends BaseEntity {
    private String serialNumber;
    private String hhuId;
    private String deviceEUI;
    private double totalLitresConsumed;
}
