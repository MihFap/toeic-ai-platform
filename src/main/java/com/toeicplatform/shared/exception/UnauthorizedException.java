package com.toeicplatform.shared.exception;

public class UnauthorizedException extends RuntimeException{
    private final String code;
    public UnauthorizedException(String message, String code) {
        super(message);
        this.code = code;
    }
    public String code() {
        return code;
    }
}
