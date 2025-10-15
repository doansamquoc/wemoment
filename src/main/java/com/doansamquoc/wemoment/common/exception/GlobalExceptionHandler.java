package com.doansamquoc.wemoment.common.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.doansamquoc.wemoment.common.enums.ErrorCode;
import com.doansamquoc.wemoment.common.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException appException, WebRequest webRequest) {
        ErrorCode code = appException.getErrorCode();

        ErrorResponse error = ErrorResponse.builder()
                .success(false)
                .status(code.getStatus().value())
                .code(code.getCode())
                .error(code.name())
                .message(code.getMessage())
                .path(webRequest.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(code.getStatus()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception exception, WebRequest webRequest) {
        ErrorResponse error = ErrorResponse.builder()
                .success(false)
                .status(500)
                .code(9000)
                .error("INTERNAL_ERROR")
                .message(exception.getMessage())
                .path(webRequest.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.internalServerError().body(error);
    }
}
