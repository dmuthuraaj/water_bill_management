package com.opzero.user.mongo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.opzero.core.mongo.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection = "client-category")
@EqualsAndHashCode(callSuper = true)
public class ClientCategory extends BaseEntity {

    private String name;
    private double price;
}
