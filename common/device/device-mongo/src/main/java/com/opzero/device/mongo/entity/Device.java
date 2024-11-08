package com.opzero.device.mongo.entity;

import com.opzero.core.mongo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "devices")
@EqualsAndHashCode(callSuper = true)
public class Device extends BaseEntity {
    private String serialNumber;
    private String hhuId;
    private String deviceEUI;
    private String clientId;
    private double totalLitresConsumed;
}
