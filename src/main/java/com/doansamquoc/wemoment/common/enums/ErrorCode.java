package com.doansamquoc.wemoment.common.enums;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    // --- AUTH (1000 -> 1999) ---
    INVALID_TOKEN(1000, HttpStatus.UNAUTHORIZED, "Invalid token"),
    TOKEN_EXPIRED(1001, HttpStatus.UNAUTHORIZED, "Token expired"),
    ACCESS_DENIED(1002, HttpStatus.FORBIDDEN, "Access denied"),

    // --- USER (2000 -> 2999) ---
    USER_NOT_FOUND(2000, HttpStatus.NOT_FOUND, "User not found"),
    EMAIL_ALREADY_EXISTS(2001, HttpStatus.CONFLICT, "Email already exists"),
    INVALID_CREDENTIALS(2002, HttpStatus.UNAUTHORIZED, "Invalid email or password"),
    USERNAME_ALREADY_EXISTS(2003, HttpStatus.CONFLICT, "Username already exists"),

    // --- SYSTEM (9000 -> 9999) ---
    INTERNAL_ERROR(9000, HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    int code;
    HttpStatus status;
    String message;
}
