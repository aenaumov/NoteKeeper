package ru.education.myproject1.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.education.myproject1.client.AuthUserWebClient;
import ru.education.myproject1.client.TokenWebClient;
import ru.education.myproject1.dto.UserTokenDto;
import ru.education.myproject1.service.AuthService;

import java.util.Base64;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthUserWebClient authUserWebClient;
    @Autowired
    private TokenWebClient tokenWebClient;
    @Autowired
    @Qualifier("refreshTokenJwtDecoder")
    private ReactiveJwtDecoder refreshTokenJwtDecoder;

    @Override
    public Mono<ResponseEntity<String>> getToken(final Mono<MultiValueMap<String, String>> formData) {

        return tokenWebClient.getToken(formData
                        .flatMap(f -> this.getUserForToken(this.convert(f))))
                .onErrorResume((ex) -> {
                    log.debug("__ERROR___ at User__service: {}", ex.getMessage());
                    return Mono.error(ex);
                });
    }

    @Override
    public Mono<ResponseEntity<String>> refreshToken(final Mono<MultiValueMap<String, String>> formData) {

        return tokenWebClient.getToken(
                        this.getUserFromJwt(
                                this.validateRefreshToken(formData)
                        )
                )
                .onErrorResume((ex) -> {
                    log.debug("__ERROR___ token: {}", ex.getMessage());
                    return Mono.error(ex);
                });

    }

    private Mono<Jwt> validateRefreshToken(final Mono<MultiValueMap<String, String>> formData) {

        return formData
                .flatMap(f -> refreshTokenJwtDecoder.decode(f.getFirst("refresh_token")));
    }

    private Mono<UserTokenDto> getUserFromJwt(final Mono<Jwt> jwt) {

        return jwt
                .map(j -> {
                    Map<String, Object> claims = j.getClaims();
                    return new UserTokenDto(
                            Long.parseLong((String) claims.get("sub")),
                            (String) claims.get("role"), true);
                });
    }

    private Mono<UserTokenDto> getUserForToken(final String authHeader) {
        return authUserWebClient.getUserForToken(authHeader)
                .map(HttpEntity::getBody);

    }

    private String convert(final MultiValueMap<String, String> form) {
        final String data = form.getFirst("username") + ":" + form.getFirst("password");
        return "Basic " + Base64.getEncoder().encodeToString(data.getBytes());
    }

}
