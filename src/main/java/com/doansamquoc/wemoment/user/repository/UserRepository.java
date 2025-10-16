package com.doansamquoc.wemoment.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.doansamquoc.wemoment.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM users u WHERE u.username = :identifier OR u.emailAddress = :identifier OR u.phoneNumber = :identifier")
    Optional<User> findByIdentifier(@Param("identifier") String identifier);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmailAddress(String emailAddress);

    Boolean existsByPhoneNumber(String phoneNumber);
}
