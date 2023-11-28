package ru.education.myproject1.service;

import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.UserTokenDto;

public interface AuthService {

    Mono<String> login(final String authHeader, final String username, final String password);
    Mono<String> refreshToken(final String authHeader, final Mono<UserTokenDto> user);

}
