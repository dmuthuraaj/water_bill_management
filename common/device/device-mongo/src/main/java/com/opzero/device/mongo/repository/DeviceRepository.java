package com.opzero.device.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.opzero.device.mongo.entity.Device;
import java.util.Optional;

public interface DeviceRepository extends MongoRepository<Device, String> {
    Optional<Device> findOneBySerialNumber(String serialNumber);

    Optional<Device> findBySerialNumberAndHhuId(String serialNumber,String hhuId);
}
