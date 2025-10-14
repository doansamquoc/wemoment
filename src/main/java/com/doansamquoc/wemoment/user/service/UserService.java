package com.doansamquoc.wemoment.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doansamquoc.wemoment.user.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

}
