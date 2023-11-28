package ru.education.myproject1.util;

import ru.education.myproject1.dto.LoginUserDto;

import java.util.Base64;

public final class LoginUserMapper {

    public LoginUserMapper() {
    }

    public static String toAuthHeader(LoginUserDto loginUserDto) {
        final String loginUser = loginUserDto.getUsername() + ":" + loginUserDto.getPassword();
        return "Basic " + Base64.getEncoder().encodeToString(loginUser.getBytes());
    }
}
