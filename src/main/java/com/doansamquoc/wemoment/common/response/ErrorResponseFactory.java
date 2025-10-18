package com.doansamquoc.wemoment.common.response;

import com.doansamquoc.wemoment.common.enums.ErrorCode;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.LocalDateTime;

public class ErrorResponseFactory {

    public static ResponseEntity<ErrorResponse> of(ErrorCode errorCode, String path) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .success(false)
                .status(errorCode.getStatus().value())
                .code(errorCode.getCode())
                .error(errorCode.name())
                .message(errorCode.getMessage())
                .path(path)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    public static ResponseEntity<ErrorResponse> of(ErrorCode errorCode, String path, String message) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .success(false)
                .status(errorCode.getStatus().value())
                .code(errorCode.getCode())
                .error(message)
                .message(errorCode.getMessage())
                .path(path)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }
}
