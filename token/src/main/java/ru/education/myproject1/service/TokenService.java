package ru.education.myproject1.service;

import ru.education.myproject1.dto.TokenDto;

public interface TokenService {

    String createAccessToken(TokenDto tokenDto);
    String createRefreshToken(TokenDto tokenDto);
}
