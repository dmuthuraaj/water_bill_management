package com.opzero.core.exception;

public interface ExceptionCodes {

    int DUPLICATE_RESOURCE_EXCEPTION = 4001;
    int RESOURCE_IN_USE_EXCEPTION = 4002;
    int RESOURCE_NOT_FOUND_EXCEPTION = 4003;
    int BAD_REQUEST = 4004;
    int FILE_FORMAT_NOT_SUPPORTED = 4005;

    int BAD_CREDENTIALS = 5001;
    int INVALID_CLIENT_ID = 5002;
    int INVALID_REDIRECT_URI = 5003;
    int AUTH_CODE_NOT_FOUND = 5004;
    int CODE_CHALLENGE_EXCEPTION = 5005;

    int TENANT_NOT_FOUND = 4004;
    int NABTO_PRODUCT_NOT_CREATED = 5001;
    int DEVICE_NOT_FOUND = 4004;
    int NABTO_DEVICE_NOT_CREATED = 5001;
    int INTERNAL_SERVER_ERROR = 5000;
    int NABTO_REQUEST_FAILED = 5003;
    int BATCH_NOT_STARTED = 5004;
}
