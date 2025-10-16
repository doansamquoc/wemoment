package com.doansamquoc.wemoment.auth.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.doansamquoc.wemoment.auth.config.JwtTokenProvider;
import com.doansamquoc.wemoment.auth.dto.AuthResponse;
import com.doansamquoc.wemoment.auth.dto.LoginRequest;
import com.doansamquoc.wemoment.auth.dto.RefreshTokenRequest;
import com.doansamquoc.wemoment.auth.entity.RefreshToken;
import com.doansamquoc.wemoment.auth.repository.RefreshTokenRepository;
import com.doansamquoc.wemoment.auth.service.AuthService;
import com.doansamquoc.wemoment.auth.service.RefreshTokenService;
import com.doansamquoc.wemoment.common.enums.ErrorCode;
import com.doansamquoc.wemoment.common.enums.TokenType;
import com.doansamquoc.wemoment.common.exception.AppException;
import com.doansamquoc.wemoment.user.entity.User;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {

    AuthenticationManager manager;
    JwtTokenProvider jwtTokenProvider;
    RefreshTokenService refreshTokenService;
    RefreshTokenRepository refreshTokenRepository;

    @Override
    public AuthResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getIdentifier(), request.getPassword());
        Authentication authenticate = manager.authenticate(authenticationToken);

        User user = (User) authenticate.getPrincipal();
        String token = jwtTokenProvider.generateToken(user.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken.getToken())
                .tokenType(TokenType.BEARER.getName())
                .build();
    }

    // @Override
    // public AuthResponse register() {
    // throw new UnsupportedOperationException("Unimplemented method 'register'");
    // }
    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.getToken())
                .map(refreshTokenService::verifyExpiration)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_TOKEN));

        User user = refreshToken.getUser();

        String newAccessToken = jwtTokenProvider.generateToken(user.getUsername());

        return AuthResponse.builder()
                .refreshToken(refreshToken.getToken())
                .accessToken(newAccessToken)
                .tokenType(TokenType.BEARER.getName())
                .build();
    }

}
