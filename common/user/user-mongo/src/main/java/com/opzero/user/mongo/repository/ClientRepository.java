package com.opzero.user.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.opzero.user.mongo.entity.Client;


public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findOneByMobileNumber(String mobileNumber);
    Optional<Client> findOneByMeterSerialNumber(String serialNumber);
}
