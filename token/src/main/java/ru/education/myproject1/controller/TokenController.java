package ru.education.myproject1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.AccessRefreshToken;
import ru.education.myproject1.dto.TokenDto;
import ru.education.myproject1.service.TokenService;


@Slf4j
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/access-refresh")
    public Mono<AccessRefreshToken> getAccessRefreshToken(@RequestBody TokenDto tokenDto) {

        return tokenService.createToken(tokenDto);
    }

}
