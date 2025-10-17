package com.doansamquoc.wemoment.auth.service;

import com.doansamquoc.wemoment.auth.dto.AuthResponse;
import com.doansamquoc.wemoment.auth.dto.LoginRequest;
import com.doansamquoc.wemoment.auth.dto.RefreshTokenRequest;
import com.doansamquoc.wemoment.user.dto.UserResponse;
import com.doansamquoc.wemoment.user.entity.User;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    public UserResponse login(LoginRequest request, HttpServletResponse response);

    public AuthResponse refreshToken(RefreshTokenRequest request);

    public void logout(User user, HttpServletResponse response);
}
