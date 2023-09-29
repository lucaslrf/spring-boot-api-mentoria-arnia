package com.api.usersintegration.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoResponseDTO {
    Long id;
    String login;
    String email;
    List<String> profiles;
}
