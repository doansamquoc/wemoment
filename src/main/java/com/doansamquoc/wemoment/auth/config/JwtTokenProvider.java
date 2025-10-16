package com.doansamquoc.wemoment.auth.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtTokenProvider {

    String JWT_SECRET = "scret_key_too_long_long_long_long_long";
    long JWT_EXPIRATION = 8640000;
    SecretKey KEY = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

    public String generateToken(String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(KEY)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException exception) {
            log.error("JWT validation error: {}", exception.getMessage());
        }
        return false;
    }
}
