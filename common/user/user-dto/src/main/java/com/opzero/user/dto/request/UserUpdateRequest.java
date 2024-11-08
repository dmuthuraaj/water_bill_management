package com.opzero.user.dto.request;

import com.opzero.core.dto.UserRole;

import lombok.Data;

@Data
public class UserUpdateRequest {

    private String name;
    private String userName;
    private UserRole role;
    private String password;
}
