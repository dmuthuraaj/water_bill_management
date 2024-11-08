package com.opzero.user.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.opzero.user.mongo.entity.ClientCategory;

public interface ClientCategoryRepository extends MongoRepository<ClientCategory, String> {

}
