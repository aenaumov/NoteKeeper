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

        log.debug("TOKEN DTO " + tokenDto.getId() +" " + tokenDto.getUserRole());

        final Mono<String> accessToken = tokenService.createAccessToken(tokenDto);
        final Mono<String> refreshToken = tokenService.createRefreshToken(tokenDto);

        log.debug("ACCESS TOKEN " + accessToken);
        log.debug("REFRESH TOKEN " + refreshToken);

        return convert(accessToken, refreshToken);
    }

    private Mono<AccessRefreshToken> convert(Mono<String> accessToken, Mono<String> refreshToken){
        return Mono.zip(accessToken, refreshToken)
                .map(tuple -> new AccessRefreshToken(tuple.getT1(), tuple.getT2()));
    }
}
