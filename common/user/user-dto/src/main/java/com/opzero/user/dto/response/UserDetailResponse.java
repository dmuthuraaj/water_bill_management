package com.opzero.user.dto.response;

import java.time.LocalDateTime;

import com.opzero.core.dto.UserRole;

import lombok.Data;

@Data
public class UserDetailResponse {

    private String id;
    private String name;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String address;
    private String mobile;
    private UserRole role;
    private String profilePic;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean active;
    private boolean delete;
}
