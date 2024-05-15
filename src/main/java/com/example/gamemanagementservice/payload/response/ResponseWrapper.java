package com.example.gamemanagementservice.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseWrapper<T> {
    private T data;
    private Integer httpCode;
    private String errMessage;

    public static <T> ResponseWrapper<T> fromError(String errorMessage, Integer httpCode) {
        return new ResponseWrapper<>(null, httpCode, errorMessage);
    }

    public static <T> ResponseWrapper<T> fromData(T data, Integer httpCode) {
        return new ResponseWrapper<>(data, httpCode, null);
    }
}
