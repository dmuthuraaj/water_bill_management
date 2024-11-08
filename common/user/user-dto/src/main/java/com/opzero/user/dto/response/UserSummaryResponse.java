package com.opzero.user.dto.response;

import java.time.LocalDateTime;

import com.opzero.core.dto.UserRole;

import lombok.Data;

@Data
public class UserSummaryResponse {

    private String id;
    private String userName;
    private String mobileNumber;
    private UserRole role;
    private LocalDateTime createdAt;
    private boolean active;
}
