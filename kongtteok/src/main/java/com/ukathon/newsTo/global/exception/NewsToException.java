package com.ukathon.newsTo.global.exception;

import org.springframework.http.HttpStatusCode;

public class NewsToException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public NewsToException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    @Override
    public String getMessage() {
        return exceptionCode.getMessage();
    }

    public HttpStatusCode getHttpStatusCode() {
        return exceptionCode.getHttpStatus();
    }

    public String getExceptionCodeName(){
        return exceptionCode.getClientExceptionCode().name();
    }

}
