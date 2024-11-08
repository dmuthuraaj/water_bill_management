package com.opzero.user.dto.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String userName;
    private String password;

}
