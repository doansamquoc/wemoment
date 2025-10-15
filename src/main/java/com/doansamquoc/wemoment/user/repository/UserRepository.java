package com.doansamquoc.wemoment.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doansamquoc.wemoment.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmailAddress(String emailAddress);

    Boolean existsByPhoneNumber(String phoneNumber);
}
