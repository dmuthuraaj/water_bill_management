package com.opzero.user.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class UserResponse {
    private String name;
    private String firstName;
    private String lastName;
    private String profilePic;
    private String email;
    private String mobile;
    private String tenant;
    private Map<String, Object> metadata;
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean active;
    private boolean delete;
}
