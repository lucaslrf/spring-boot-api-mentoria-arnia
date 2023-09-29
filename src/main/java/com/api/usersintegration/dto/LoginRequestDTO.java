package com.api.usersintegration.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    String login;
    String password;
}
