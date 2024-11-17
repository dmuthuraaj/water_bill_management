package com.opzero.device.mongo.repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.opzero.device.mongo.entity.MeterReading;

public interface MeterReadingRepository extends MongoRepository<MeterReading, String> {
    List<MeterReading> findBySerialNumber(String serialNumber);

    List<MeterReading> findByDeviceId(String deviceId);

    Optional<MeterReading> findByDeviceIdAndMonth(String deviceId,Month month);

    List<MeterReading> findBySerialNumberAndHhuId(String serialNumber,String hhuId);

    Optional<MeterReading> findBySerialNumberAndDate(String serialNumber, LocalDate date);

    Optional<MeterReading> findBySerialNumberAndDateAndHhuId(String serialNumber, LocalDate date,String hhuId);

    Optional<MeterReading> findBySerialNumberAndMonthAndHhuId(String serialNumber, Month month,String hhuId);

//    Optional<MeterReading> findBySerialNumberAndMonth(String serialNumber, Month month)


}
