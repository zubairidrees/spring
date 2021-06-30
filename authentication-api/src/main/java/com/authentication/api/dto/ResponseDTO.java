package com.authentication.api.dto;

public class ResponseDTO {

    private String responseMessage;
    private ResponseUserDTO responseUserDTO;
    private Integer responseCode;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ResponseUserDTO getResponseUserDTO() {
        return responseUserDTO;
    }

    public void setResponseUserDTO(ResponseUserDTO responseUserDTO) {
        this.responseUserDTO = responseUserDTO;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }
}
