package com.api.usersintegration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.usersintegration.dto.UserRequestDTO;
import com.api.usersintegration.mapper.UserMapper;
import com.api.usersintegration.model.User;
import com.api.usersintegration.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
   
    @Autowired
    private UserMapper userMapper;

    // // protected final UserMapper userMapper;

    // @Autowired
    // public UserService(UserRepository userRepository, UserMapper userMapper) {        
    //     this.userMapper = userMapper;
    //     this.userRepository = userRepository;
    // }

    public List<User> listUsers(){
        return this.userRepository.findAll();
    }

    public Long registerUser(UserRequestDTO userRequestDTO){
        // User user = new User();
        // user.setEmail(userRequestDTO.getEmail());
        // user.setLogin(userRequestDTO.getLogin());
        // user.setName(userRequestDTO.getName());   
        User user = userMapper.toUser(userRequestDTO);
        System.out.println("TESTE_SERVICE: "+user.getEmail());
        return userRepository.save(user).getId();        
    } 

    public String updateUser(UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(userRequestDTO.getId()).orElseThrow();
        user.setEmail(userRequestDTO.getEmail());
        user.setLogin(userRequestDTO.getLogin());
        user.setName(userRequestDTO.getName());
        userRepository.save(user);
        return "USUÁRIO ATUALIZADO COM SUCESSO";
           
    } 

}
