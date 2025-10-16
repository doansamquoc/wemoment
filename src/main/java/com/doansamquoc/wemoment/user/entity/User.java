package com.doansamquoc.wemoment.user.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null)
            return Collections.emptySet();
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).toList();
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
