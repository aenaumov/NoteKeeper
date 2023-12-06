package ru.education.myproject1.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.UserTokenDto;
import ru.education.myproject1.service.JwtRefreshService;

import java.util.Map;

@Component
public class JwtRefreshServiceImpl implements JwtRefreshService {

    @Autowired
    @Qualifier("refreshTokenJwtDecoder")
    private ReactiveJwtDecoder refreshTokenJwtDecoder;

    @Override
    public Mono<Jwt> validateRefreshToken(final Mono<MultiValueMap<String, String>> formData) {

        return formData
                .flatMap(f -> refreshTokenJwtDecoder.decode(f.getFirst("refresh_token")));
    }

    @Override
    public Mono<UserTokenDto> getUserFromJwt(final Mono<Jwt> jwt){

        return jwt
                .map(j -> {
                    Map<String, Object> claims = j.getClaims();
                    return new UserTokenDto(
                            Long.parseLong((String) claims.get("sub")),
                            (String) claims.get("role"));
                });
    }
}