package com.opzero.user.mongo.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import com.opzero.core.mongo.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@Document(collection = "clients")
@EqualsAndHashCode(callSuper = true)
public class Client extends BaseEntity {

    private String name;
    private String mobileNumber;
    private String rrNumber;
    private String address;
    private String meterSerialNumber;
    private String category;
    private double previousReading;
    private String deviceId;
    private String zoneNumber;
    private String wardNumber;
    private String existingConsumerNumber;
    private double waterBillBalance;
    private String newMeterNumber;
    private Date dateOfFixing;
    private String connectionType; // domestic
    private String buildingOwnerName;
    private String aadharNumber;
    private int municipalKhataNumber;
    private int numberOfHousesInTheBuilding;
    private int numberOfPersonsInTheBuilding;
    // private PaymentInfo paymentInfo;
}
