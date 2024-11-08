package com.opzero.user.dto.request;

import com.opzero.core.dto.UserRole;

import lombok.Data;

@Data
public class UserAddRequest {
    private String name;
    private String mobileNumber;
    private String email;
    private String password;
    private UserRole role;
}
