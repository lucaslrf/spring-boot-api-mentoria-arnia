package com.api.usersintegration.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.usersintegration.dto.UserRequestDTO;
import com.api.usersintegration.model.User;
import com.api.usersintegration.repository.UserRepository;
import com.api.usersintegration.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> listUsers(){
        return this.userService.listUsers();
    }

    @PostMapping
    public String postUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        Long idUser = userService.registerUser(userRequestDTO);
        return "OK - ID DO USUARIO: "+idUser;
    }

    @PutMapping
    public String putUser(@RequestBody UserRequestDTO userRequestDTO){
        return userService.updateUser(userRequestDTO);        
    }

    @DeleteMapping
    public String deleteUser(){
        return "";
    }
}
