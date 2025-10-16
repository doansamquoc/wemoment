package com.doansamquoc.wemoment.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doansamquoc.wemoment.auth.dto.AuthResponse;
import com.doansamquoc.wemoment.auth.dto.LoginRequest;
import com.doansamquoc.wemoment.auth.service.AuthService;
import com.doansamquoc.wemoment.common.response.ApiResponse;
import com.doansamquoc.wemoment.common.response.ResponseFactory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest request) {
        AuthResponse authResponse = authService.login(request);
        return ResponseFactory.success(authResponse, "Success");
    }
}
