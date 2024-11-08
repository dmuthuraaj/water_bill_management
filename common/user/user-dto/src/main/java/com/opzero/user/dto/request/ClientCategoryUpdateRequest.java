package com.opzero.user.dto.request;

import lombok.Data;

@Data
public class ClientCategoryUpdateRequest {

    private String name;
    private boolean active;
}
