package com.doansamquoc.wemoment.user.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.doansamquoc.wemoment.common.enums.AuthProvider;
import com.doansamquoc.wemoment.common.enums.Gender;
import com.doansamquoc.wemoment.common.enums.Role;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponse {
    String id;
    String username;
    String firstName;
    String lastName;
    String displayName;
    String avatarUrl;
    String bio;
    LocalDate dateOfBirth;
    Gender gender;
    String address;
    String emailAddress;
    String phoneNumber;
    boolean isActive;
    Set<Role> roles;
    Set<AuthProvider> providers;
    LocalDateTime updatedAt;
    LocalDateTime createdAt;
}
