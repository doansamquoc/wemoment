package com.doansamquoc.wemoment.common.response;

import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseFactory {

    public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .data(data)
                .message(message)
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<ApiResponse<?>> success(String message) {
        ApiResponse<?> response = ApiResponse.builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message(message)
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T data, String message) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .data(data)
                .message(message)
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
