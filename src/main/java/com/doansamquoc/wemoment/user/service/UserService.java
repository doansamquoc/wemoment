package com.doansamquoc.wemoment.user.service;

import com.doansamquoc.wemoment.user.dto.UserCreationRequest;
import com.doansamquoc.wemoment.user.dto.UserResponse;

public interface UserService {
    public UserResponse createUser(UserCreationRequest request);

    public UserResponse findByUserName(String username);
}
