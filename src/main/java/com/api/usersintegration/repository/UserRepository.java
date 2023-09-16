package com.api.usersintegration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.usersintegration.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    @Query("select count(*) from User u")
    int countUsers();

    @Query("select u from User u WHERE u.login like %:login%")
    User getUserByLogin(String login);

    @Query("select u from User u WHERE u.name like %:name%")
    List<User> getUsersByName(String name);

    Boolean existsByEmail(String email);

    Boolean existsByLogin(String login);
    
    @Query("select u from User u WHERE u.login like %:login% or u.email like %:email%")
    User existUserByLoginOrEmail(String email, String login);

}
