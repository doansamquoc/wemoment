package com.doansamquoc.wemoment.user.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.doansamquoc.wemoment.common.enums.AuthProvider;
import com.doansamquoc.wemoment.common.enums.ErrorCode;
import com.doansamquoc.wemoment.common.enums.Role;
import com.doansamquoc.wemoment.common.exception.AppException;
import com.doansamquoc.wemoment.user.dto.UserCreationRequest;
import com.doansamquoc.wemoment.user.dto.UserResponse;
import com.doansamquoc.wemoment.user.entity.User;
import com.doansamquoc.wemoment.user.mapper.UserMapper;
import com.doansamquoc.wemoment.user.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        if (userRepository.existsByEmailAddress(request.getEmailAddress())) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        User user = userMapper.toUserCreationRequest(request);

        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);

        Set<AuthProvider> providers = new HashSet<>();
        providers.add(AuthProvider.LOCAL);

        user.setRoles(roles);
        user.setProviders(providers);

        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }
}
