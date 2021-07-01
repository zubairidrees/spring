package com.authentication.api.util;


public enum ResponseCodes {
    OK(200,"Success"),UNAUTHORIZED(401,"Bad Credentials"),FORBIDDEN(403,"Forbidden");
    private int code;
    private String desc;

    ResponseCodes(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
