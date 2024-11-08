package com.opzero.user.dto.request;

import lombok.Data;

@Data
public class SignupRequest {
    private String name;
    private String mobileNumber;
    private String email;
    private String password;
}
