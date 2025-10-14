package com.doansamquoc.wemoment.user.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.doansamquoc.wemoment.common.enums.AuthProvider;
import com.doansamquoc.wemoment.common.enums.Gender;
import com.doansamquoc.wemoment.common.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(unique = true, nullable = false)
    String username;

    @Column(nullable = false)
    String hashedPassword;

    @Column(nullable = false)
    String firstName;
    String lastName;

    @Column(nullable = false)
    String displayName;
    String avatarUrl;
    String bio;
    LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    Gender gender;
    String address;
    String emailAddress;

    @Column(nullable = true)
    String phoneNumber;

    boolean isActive;

    @Enumerated(EnumType.STRING)
    Set<AuthProvider> providers;

    @Enumerated(EnumType.STRING)
    Set<Role> roles;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @CreationTimestamp
    LocalDateTime createdAt;
}
