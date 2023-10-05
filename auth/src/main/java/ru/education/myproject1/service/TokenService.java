package ru.education.myproject1.service;

import ru.education.myproject1.dto.UserToken;

public interface TokenService {

    String createAccessToken(UserToken userToken);
}
