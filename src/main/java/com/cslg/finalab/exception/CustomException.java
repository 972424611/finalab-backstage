package com.cslg.finalab.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private Integer code;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
