package com.api.usersintegration.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.usersintegration.dto.UserRequestDTO;
import com.api.usersintegration.model.User;
import com.api.usersintegration.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> listUsers(@RequestParam(required = false) String name){
        if(name != null){
            return this.userService.listUsers(name);
        }
        return this.userService.listUsers();
    }


    @GetMapping(path = "/count")
    public int countUsers(){
        return this.userService.countUsers();
    }

    @GetMapping(path = "/existEmail")
    @Pageab
    public Boolean existUser(@RequestParam String email){
        return this.userService.existUser(email);
    }

    @GetMapping(path = "/login")
    public User getUserByLogin(@RequestParam String login){
        return this.userService.userByLogin(login);
    }

    @PostMapping
    public String postUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        Long idUser = userService.registerUser(userRequestDTO);
        return "OK - ID DO USUARIO: "+idUser;
    }

    @PutMapping(path = "/{id}")
    public String putUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO){
        System.out.println("ID: "+id);
        return userService.updateUser(id, userRequestDTO);        
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);        
    }
}
