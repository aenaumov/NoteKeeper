package ru.education.myproject1.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.education.myproject1.service.AuthService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/token",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Mono<ResponseEntity<String>> getToken(ServerWebExchange serverWebExchange) {

        log.debug("Received request at auth/token end-point");
        return authService.getToken(serverWebExchange.getFormData());
    }

    @PostMapping("/refresh")
    public Mono<ResponseEntity<String>> refreshToken(ServerWebExchange serverWebExchange) {

        log.debug("Received request at auth/refresh end-point");
        return authService.refreshToken(serverWebExchange.getFormData());
    }

}
