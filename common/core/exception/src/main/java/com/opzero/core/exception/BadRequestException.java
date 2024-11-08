package com.opzero.core.exception;


public class BadRequestException extends BaseException {

    public BadRequestException(String message) {
        super(message, ExceptionCodes.BAD_REQUEST);
    }

}
