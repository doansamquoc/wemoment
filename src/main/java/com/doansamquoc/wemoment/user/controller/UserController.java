package com.doansamquoc.wemoment.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doansamquoc.wemoment.common.response.ApiResponse;
import com.doansamquoc.wemoment.common.response.ResponseFactory;
import com.doansamquoc.wemoment.user.dto.UserCreationRequest;
import com.doansamquoc.wemoment.user.dto.UserResponse;
import com.doansamquoc.wemoment.user.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody UserCreationRequest request) {
        UserResponse userResponse = userService.createUser(request);
        return ResponseFactory.created(userResponse, "User created successfully");
    }

    @GetMapping("/{username}")
    public ResponseEntity<ApiResponse<UserResponse>> findByUsername(@PathVariable String username) {
        UserResponse userResponse = userService.findByUserName(username);
        return ResponseFactory.success(userResponse, "Success");
    }
}
