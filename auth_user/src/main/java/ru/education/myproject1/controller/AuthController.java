package ru.education.myproject1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.UserTokenDto;
import ru.education.myproject1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@Slf4j
@RestController
@RequestMapping("/user/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public Mono<UserTokenDto> getUser(@AuthenticationPrincipal Mono<Principal> principal) {

        return principal
                .map(Principal::getName)
                .flatMap(username -> userService.getUserForToken(username));
    }

}
