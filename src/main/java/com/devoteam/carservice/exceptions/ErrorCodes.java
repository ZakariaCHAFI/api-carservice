package com.devoteam.carservice.exceptions;

public enum ErrorCodes {
    CAR_NOT_FOUND(4004),

    LEASING_NOT_VALID(1001),
    RETURN_LEASED_NOT_VALID(1002),
    LEASING_ALREADY_IN_USE(1003)
    ;
    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}
