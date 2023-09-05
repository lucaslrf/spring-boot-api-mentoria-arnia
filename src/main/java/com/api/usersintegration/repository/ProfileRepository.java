package com.api.usersintegration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.usersintegration.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer>{
    
}
