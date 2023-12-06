package ru.education.myproject1.service;

import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.UserTokenDto;

public interface AuthService {

    Mono<String> login(final String authHeader, final Mono<MultiValueMap<String, String>> formData);
    Mono<String> refreshToken(final String authHeader, final Mono<UserTokenDto> user);

}
