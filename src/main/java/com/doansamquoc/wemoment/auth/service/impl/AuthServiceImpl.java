package com.doansamquoc.wemoment.auth.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
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
import com.doansamquoc.wemoment.common.utils.CookieUtils;
import com.doansamquoc.wemoment.user.dto.UserResponse;
import com.doansamquoc.wemoment.user.entity.User;
import com.doansamquoc.wemoment.user.mapper.UserMapper;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {

    UserMapper userMapper;
    AuthenticationManager manager;
    JwtTokenProvider jwtTokenProvider;
    RefreshTokenService refreshTokenService;
    RefreshTokenRepository refreshTokenRepository;

    @Override
    public UserResponse login(LoginRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getIdentifier(), request.getPassword());
        Authentication authenticate = manager.authenticate(authenticationToken);

        User user = (User) authenticate.getPrincipal();
        String token = jwtTokenProvider.generateToken(user.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        ResponseCookie accessCookie = ResponseCookie.from("accessToken", token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(15 * 60)
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken.getToken())
                .httpOnly(true)
                .secure(true)
                .path("/api/auth/refresh")
                .maxAge(7 * 24 * 60 * 60)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        return userMapper.toUserResponse(user);
    }

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

    @Override
    public void logout(User user, HttpServletResponse response) {
        refreshTokenRepository.deleteByUser(user);
        ResponseCookie clearRefresh = CookieUtils.deleteCookie("refreshToken", "/api/auth/refresh");
        ResponseCookie clearAccess = CookieUtils.deleteCookie("accessToken", "/");

        CookieUtils.addCookieToHeader(response, clearAccess);
        CookieUtils.addCookieToHeader(response, clearRefresh);
    }
}
