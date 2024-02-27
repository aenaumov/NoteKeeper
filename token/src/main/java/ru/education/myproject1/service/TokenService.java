package ru.education.myproject1.service;

import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.AccessRefreshToken;
import ru.education.myproject1.dto.TokenDto;

public interface TokenService {
    Mono<AccessRefreshToken> createToken(TokenDto tokenDto);
}
