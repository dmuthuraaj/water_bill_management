package com.opzero.core.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoResponse {

    private String sub;

    private String name;

    private String given_name;

    private String family_name;

    private String preferred_username;


    private String picture;

    private String email;

    private String phone_number;

    private Set<String> roles;

    private String updated_at;

    private String error;

    private String error_description;

    public UserInfoResponse() {
    }

    public UserInfoResponse(String subject) {
        this.sub = subject;
        this.name = subject;
    }

    public UserInfoResponse(String error, String error_description) {
        this.error = error;
        this.error_description = error_description;
    }

    public UserInfoResponse(String identifier, String firstname, String lastname, String email,String updatedAt, Set<String> roles) {
        this.sub = identifier;
        this.name = firstname + " " + lastname;
        this.given_name = firstname;
        this.family_name = lastname;
        this.preferred_username = email;
        this.email = email;
        this.roles = roles;
        this.updated_at = updatedAt;
    }


}
