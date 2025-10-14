package com.doansamquoc.wemoment.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doansamquoc.wemoment.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
