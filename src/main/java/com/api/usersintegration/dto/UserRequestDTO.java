package com.api.usersintegration.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {  
    private Long id;  
    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    private String login;
    @NotEmpty
    private String email;

    @Override
    public String toString(){
        return getLogin()+" - "+getName()+" - "+getEmail();
    }
}
