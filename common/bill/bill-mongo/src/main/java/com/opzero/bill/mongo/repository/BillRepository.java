package com.opzero.bill.mongo.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.opzero.bill.mongo.entity.Bill;


public interface BillRepository extends MongoRepository<Bill, String> {
    Optional<Bill> findByReadingDate(LocalDate date);
}
