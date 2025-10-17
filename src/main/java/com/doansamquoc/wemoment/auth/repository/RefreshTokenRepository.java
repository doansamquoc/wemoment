package com.doansamquoc.wemoment.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.doansamquoc.wemoment.auth.entity.RefreshToken;
import com.doansamquoc.wemoment.user.entity.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUser(User user);

    boolean existsByUser(User user);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    void deleteByUser(User user);
}
