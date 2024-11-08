package com.opzero.user.mongo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.opzero.core.dto.UserRole;
import com.opzero.core.mongo.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    private String name;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String address;
    private String mobileNumber;
    private UserRole role;
    private String profilePic;
}
