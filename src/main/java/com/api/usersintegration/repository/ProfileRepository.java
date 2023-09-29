package com.api.usersintegration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.usersintegration.enums.ProfileEnum;
import com.api.usersintegration.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer>{
    Optional<Profile> findByName(ProfileEnum name);
}
