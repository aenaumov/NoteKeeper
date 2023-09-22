package ru.education.myproject1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController

@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id,
                          @AuthenticationPrincipal Jwt principal) {
        System.out.println("claims " + principal.getClaims());
        return "it is OK";
    }
}
