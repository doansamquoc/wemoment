package com.doansamquoc.wemoment.auth.service;

import com.doansamquoc.wemoment.auth.entity.RefreshToken;
import com.doansamquoc.wemoment.user.entity.User;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(User user);

    RefreshToken verifyExpiration(RefreshToken token);
}
