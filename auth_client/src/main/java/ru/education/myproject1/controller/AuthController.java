package ru.education.myproject1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
@RequestMapping("/client/auth")
public class AuthController {

    @GetMapping("/authentication")
    public Mono<Boolean> authentication(Authentication authentication) {

        log.info(authentication.isAuthenticated()? "Client is authorized" : "__FALSE__");

        return Mono.just(authentication.isAuthenticated());
    }
}
