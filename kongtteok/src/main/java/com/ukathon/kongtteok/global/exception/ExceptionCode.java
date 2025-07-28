package com.ukathon.kongtteok.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {

    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, ClientExceptionCode.USER_NOT_FOUND, "찾을 수 없는 유저입니다."),
    HANDLE_ALREADY_EXISTS(HttpStatus.CONFLICT, ClientExceptionCode.HANDLE_ALREADY_EXISTS, "이미 존재하는 아이디입니다.");

    private final HttpStatus httpStatus;
    private final ClientExceptionCode clientExceptionCode;
    private final String message;

    ExceptionCode(HttpStatus httpStatus, ClientExceptionCode clientExceptionCode, String message) {
        this.httpStatus = httpStatus;
        this.clientExceptionCode = clientExceptionCode;
        this.message = message;
    }
}
