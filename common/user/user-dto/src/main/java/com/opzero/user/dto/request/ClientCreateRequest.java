package com.opzero.user.dto.request;

import java.util.Date;

import lombok.Data;

@Data
public class ClientCreateRequest {

    private String category;
    private String name;
    private String mobileNumber;
    private String address;
    // private String deviceId;
    // private String meterSerialNumber;
    private String rrNumber;
    private double previousReading;
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
}
