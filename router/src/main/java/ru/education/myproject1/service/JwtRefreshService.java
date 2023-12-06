package ru.education.myproject1.service;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.UserTokenDto;

public interface JwtRefreshService {

    Mono<Jwt> validateRefreshToken(final Mono<MultiValueMap<String, String>> formData);
    Mono<UserTokenDto> getUserFromJwt(final Mono<Jwt> jwt);

}
