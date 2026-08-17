package com.example.project.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final String code;
    private final int status;

    public BusinessException(String code, String message) {
        this(code, message, 400);
    }

    public BusinessException(String code, String message, int status) {
        super(message);
        this.code = code;
        this.status = status;
    }
}