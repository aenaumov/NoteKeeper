package ru.education.myproject1.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.education.myproject1.service.AuthService;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth/jwt")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/get")
    public Mono<String> getJwt(@RequestHeader("Authorization") String authHeader) {
        return authService.getJwt(authHeader);
    }
}
