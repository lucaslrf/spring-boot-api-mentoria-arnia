package com.api.usersintegration.dto;

import java.util.Set;

import org.springframework.boot.context.config.Profiles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {
    String login;
    String email;
    String password;
    Set<String> profile;
}
