package com.api.usersintegration.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @Parameters({
        @Parameter(name="page"),
        @Parameter(name="size"),
        @Parameter(name="sort")
    })
    public Page<User> listUsers(
            @PageableDefault(page=0, size=200)
            @SortDefault.SortDefaults({
                @SortDefault(sort="name", direction = Sort.Direction.ASC),
                @SortDefault(sort="id", direction = Sort.Direction.DESC)
            })
            @Parameter(hidden = true) Pageable pageable,
            @RequestParam(required = false) String name
        ){
        return this.userService.listUsers(name, pageable);
    }


    @GetMapping(path="/allUsers")
    @Parameters({
        @Parameter(name="page"),
        @Parameter(name="size"),
        @Parameter(name="sort")
    })
    public Page<User> listAllUsers(
        @PageableDefault(page=0, size=200)
            @SortDefault.SortDefaults({
                @SortDefault(sort="name", direction = Sort.Direction.ASC),
                @SortDefault(sort="id", direction = Sort.Direction.DESC)
            })
            @Parameter(hidden = true) Pageable pageable
    ){
        return this.userService.listUsers(pageable);
    }


    @GetMapping(path = "/count")
    public int countUsers(){
        return this.userService.countUsers();
    }

    @GetMapping(path = "/existEmail")
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
