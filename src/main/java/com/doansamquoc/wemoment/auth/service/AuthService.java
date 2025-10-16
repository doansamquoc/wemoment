package com.doansamquoc.wemoment.auth.service;

import com.doansamquoc.wemoment.auth.dto.AuthResponse;
import com.doansamquoc.wemoment.auth.dto.LoginRequest;
import com.doansamquoc.wemoment.auth.dto.RefreshTokenRequest;

public interface AuthService {
    public AuthResponse login(LoginRequest request);

    public AuthResponse refreshToken(RefreshTokenRequest request);
}
