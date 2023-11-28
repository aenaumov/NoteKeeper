package ru.education.myproject1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginUserDto {

    private String grant_type;
    private String username;
    private String password;
}
