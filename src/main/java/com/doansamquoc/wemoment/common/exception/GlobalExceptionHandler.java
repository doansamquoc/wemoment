package com.doansamquoc.wemoment.common.exception;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

import com.doansamquoc.wemoment.common.response.ErrorResponseFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.doansamquoc.wemoment.common.enums.ErrorCode;
import com.doansamquoc.wemoment.common.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException appException, WebRequest webRequest) {
        ErrorCode errorCode = appException.getErrorCode();
        return ErrorResponseFactory.of(errorCode, webRequest.getDescription(false), appException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception exception, WebRequest webRequest) {
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        return ErrorResponseFactory.of(errorCode, webRequest.getDescription(false), exception.getMessage());
    }

    @ExceptionHandler({BadCredentialsException.class, InternalAuthenticationServiceException.class, UsernameNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleAuthErrors(Exception exception, WebRequest webRequest) {
        ErrorCode errorCode = ErrorCode.INVALID_CREDENTIALS;
        return ErrorResponseFactory.of(errorCode, webRequest.getDescription(false), exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException exception, WebRequest webRequest) {
        ErrorCode errorCode = ErrorCode.ACCESS_DENIED;
        return ErrorResponseFactory.of(errorCode, webRequest.getDescription(false), exception.getMessage());
    }
}
