package com.doansamquoc.wemoment.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doansamquoc.wemoment.auth.dto.LoginRequest;
import com.doansamquoc.wemoment.auth.service.AuthService;
import com.doansamquoc.wemoment.common.response.ApiResponse;
import com.doansamquoc.wemoment.common.response.ResponseFactory;
import com.doansamquoc.wemoment.user.dto.UserResponse;
import com.doansamquoc.wemoment.user.entity.User;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
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
    public ResponseEntity<ApiResponse<UserResponse>> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        UserResponse userResponse = authService.login(request, response);
        return ResponseFactory.success(userResponse, "Success");
    }

    @Transactional
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(Authentication authentication, HttpServletResponse response) {
        User user = (User) authentication.getPrincipal();
        authService.logout(user, response);
        return ResponseFactory.success("Logged out successfully");
    }

}
