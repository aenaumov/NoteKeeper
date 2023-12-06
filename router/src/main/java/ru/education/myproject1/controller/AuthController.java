package ru.education.myproject1.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.UserTokenDto;
import ru.education.myproject1.service.AuthService;
import ru.education.myproject1.service.JwtRefreshService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtRefreshService jwtRefreshService;

    @PostMapping(path = "/login",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Mono<String> login(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                              ServerWebExchange serverWebExchange) {

        log.debug("Received request at auth/login end-point");
        final Mono<MultiValueMap<String, String>> formData = serverWebExchange.getFormData();
        return authService.login(authHeader, formData);
    }

    @PostMapping("/refresh")
    public Mono<String> getAccessToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                       ServerWebExchange serverWebExchange) {

        log.debug("Received request at auth/refresh end-point");
        final Mono<MultiValueMap<String, String>> formData = serverWebExchange.getFormData();
        final Mono<Jwt> jwt = jwtRefreshService.validateRefreshToken(formData);
        final Mono<UserTokenDto> userTokenDtoMono = jwtRefreshService.getUserFromJwt(jwt);

        return authService.refreshToken(authHeader, userTokenDtoMono);
    }

}
