package com.opzero.user.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.opzero.user.mongo.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByMobileNumber(String mobile);

    boolean existsByMobileNumber(String mobile);
}
