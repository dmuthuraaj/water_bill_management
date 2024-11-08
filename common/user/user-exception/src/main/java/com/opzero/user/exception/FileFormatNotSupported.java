package com.opzero.user.exception;

import com.opzero.core.exception.ExceptionCodes;

import lombok.Getter;

@Getter
public class FileFormatNotSupported extends RuntimeException {
    private final int statueCode;

    public FileFormatNotSupported(String message) {
        super(message);
        this.statueCode = ExceptionCodes.FILE_FORMAT_NOT_SUPPORTED;
    }
}
