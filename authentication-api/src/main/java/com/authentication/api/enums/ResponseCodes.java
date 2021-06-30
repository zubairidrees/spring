package com.authentication.api.enums;

public enum ResponseCodes {

    RESPONSE_OK(200),
    RESPONSE_UNAUTHORIZED(401),
    RESPONSE_FORBIDDEN(403);

    public final Integer responseCode;

    ResponseCodes(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Integer getResponseCode() {
        return responseCode;
    }
}
