package com.opzero.core.exception;

public class ResourceNotExistException extends BaseException{

    public ResourceNotExistException(String msg) {
        super(msg,ExceptionCodes.RESOURCE_NOT_FOUND_EXCEPTION);
    }
}
