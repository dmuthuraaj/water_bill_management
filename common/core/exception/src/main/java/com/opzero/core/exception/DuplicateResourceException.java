package com.opzero.core.exception;

public class DuplicateResourceException extends BaseException{

    public DuplicateResourceException(String msg) {
        super(msg,ExceptionCodes.DUPLICATE_RESOURCE_EXCEPTION);
    }
}
