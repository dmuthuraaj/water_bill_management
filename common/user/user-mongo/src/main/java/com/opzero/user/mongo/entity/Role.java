package com.opzero.user.mongo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.opzero.core.mongo.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection = "roles")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity{
    private String name;
}
