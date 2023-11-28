package ru.education.myproject1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.UserTokenDto;
import ru.education.myproject1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Mono<UserTokenDto> login(@AuthenticationPrincipal Mono<Principal> principal) {

        final Mono<UserTokenDto> userTokenDtoMono = principal
                .map(Principal::getName)
                .flatMap(username -> userService.getUserForToken(username));

        return userTokenDtoMono;

    }

}
