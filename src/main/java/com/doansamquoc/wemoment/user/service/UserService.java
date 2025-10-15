package com.doansamquoc.wemoment.user.service;

import org.springframework.stereotype.Service;

import com.doansamquoc.wemoment.user.dto.UserCreationRequest;
import com.doansamquoc.wemoment.user.dto.UserResponse;

@Service
public interface UserService {
    public UserResponse createUser(UserCreationRequest request);
}
