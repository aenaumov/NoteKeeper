package ru.education.myproject1.service;

import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.TokenDto;

public interface TokenService {

    Mono<String> createAccessToken(TokenDto tokenDto);
    Mono<String> createRefreshToken(TokenDto tokenDto);
}
