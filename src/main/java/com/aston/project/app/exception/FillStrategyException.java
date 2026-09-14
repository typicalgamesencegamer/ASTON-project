package com.aston.project.app.exception;

public class FillStrategyException extends RuntimeException {
    private final ErrorCode errorCode;

    public FillStrategyException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}