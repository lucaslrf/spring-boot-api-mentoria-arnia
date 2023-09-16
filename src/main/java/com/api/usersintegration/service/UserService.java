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

    public List<User> listUsers(){
        return this.userRepository.findAll();
    }
    
    public List<User> listUsers(String name){
        return this.userRepository.getUsersByName(name);
    }

    public Long registerUser(UserRequestDTO userRequestDTO){      
        if(userRepository.existsByEmail(userRequestDTO.getEmail()) == true){
             return 0L;
        }
        User user = userMapper.toUser(userRequestDTO);
        System.out.println("TESTE_SERVICE: "+user.getEmail());
        return userRepository.save(user).getId();        
    }

    public String updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id).orElseThrow();
        user.setEmail(userRequestDTO.getEmail());
        user.setLogin(userRequestDTO.getLogin());
        user.setName(userRequestDTO.getName());
        // user = userMapper.toUser(userRequestDTO);
        userRepository.save(user);
        return "USUÁRIO ATUALIZADO COM SUCESSO";
           
    } 

    public String deleteUser(Long id) {        
        userRepository.deleteById(id);
        return "USUÁRIO DELETADO COM SUCESSO";
    } 

    public int countUsers() {
        return userRepository.countUsers();
    }

    public Boolean existUser(String email) {
        return userRepository.existsByEmail(email);
    }

    public User userByLogin(String login) {
        System.out.println("LOGIN: "+login);
        return userRepository.getUserByLogin(login);
    }

}
