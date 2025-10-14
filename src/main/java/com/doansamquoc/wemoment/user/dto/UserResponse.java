package com.doansamquoc.wemoment.user.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.doansamquoc.wemoment.common.enums.Gender;

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
    LocalDateTime updatedAt;
    LocalDateTime createdAt;
}
