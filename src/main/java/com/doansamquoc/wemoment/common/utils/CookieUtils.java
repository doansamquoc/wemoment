package com.doansamquoc.wemoment.common.utils;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtils {

    public static ResponseCookie createCookie(String name, String value, long maxAge, String path) {
        return ResponseCookie.from(name, value)
                .httpOnly(true)
                .secure(true)
                .path(path)
                .sameSite("Strict")
                .maxAge(maxAge)
                .build();
    }

    public static ResponseCookie deleteCookie(String name, String path) {
        return ResponseCookie.from(name)
                .httpOnly(true)
                .secure(true)
                .path(path)
                .sameSite("Strict")
                .maxAge(0)
                .build();
    }

    public static void addCookieToHeader(HttpServletResponse response, ResponseCookie cookie) {
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals(name)).findFirst();
        }
        return Optional.empty();
    }
}
