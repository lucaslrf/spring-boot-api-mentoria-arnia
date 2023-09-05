package com.api.usersintegration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.usersintegration.model.User;
import com.api.usersintegration.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public List<User> listUsers(){
        return this.userRepository.findAll();
    }

}
