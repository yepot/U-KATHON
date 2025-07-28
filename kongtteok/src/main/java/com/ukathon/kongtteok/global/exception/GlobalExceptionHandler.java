package com.ukathon.kongtteok.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(KongtteokException.class)
    public ResponseEntity<?> handleKongtteokException(KongtteokException e, HttpServletRequest request) {

        Map<String, Object> error = new LinkedHashMap<>();
        error.put("timestamp", ZonedDateTime.now());
        error.put("status", e.getHttpStatusCode().value());
        error.put("error", e.getHttpStatusCode());
        error.put("message", e.getMessage());
        error.put("code", e.getExceptionCodeName());
        error.put("path", request.getRequestURI());

        return ResponseEntity.status(e.getHttpStatusCode()).body(error);
    }
}
