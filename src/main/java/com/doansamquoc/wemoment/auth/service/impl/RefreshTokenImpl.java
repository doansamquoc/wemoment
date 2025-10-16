package com.doansamquoc.wemoment.auth.service.impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.doansamquoc.wemoment.auth.entity.RefreshToken;
import com.doansamquoc.wemoment.auth.repository.RefreshTokenRepository;
import com.doansamquoc.wemoment.auth.service.RefreshTokenService;
import com.doansamquoc.wemoment.common.enums.ErrorCode;
import com.doansamquoc.wemoment.common.exception.AppException;
import com.doansamquoc.wemoment.user.entity.User;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RefreshTokenImpl implements RefreshTokenService {

    RefreshTokenRepository refreshTokenRepository;
    static long REFRESH_TOKEN_DURATION_MS = 7 * 24 * 60 * 60 * 1000l;

    @Override
    public RefreshToken createRefreshToken(User user) {
        Optional<RefreshToken> existingToken = refreshTokenRepository.findByUser(user);
        RefreshToken token = existingToken.orElseGet(() -> new RefreshToken());
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Instant.now().plusMillis(REFRESH_TOKEN_DURATION_MS));
        return refreshTokenRepository.save(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new AppException(ErrorCode.TOKEN_EXPIRED);
        }
        return token;
    }

}
