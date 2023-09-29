package com.api.usersintegration.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.usersintegration.model.User;
import com.api.usersintegration.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User user = userRepository.getUserByLogin(login);

    return UserDetailsImpl.build(user);
    }
}
