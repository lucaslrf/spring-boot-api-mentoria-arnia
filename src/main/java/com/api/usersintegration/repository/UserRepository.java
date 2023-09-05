package com.api.usersintegration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.usersintegration.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
}
