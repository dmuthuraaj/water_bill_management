package com.opzero.core.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final int statueCode;

    public BaseException(String message, int statueCode) {
        super(message);
        this.statueCode = statueCode;
    }
    
}
