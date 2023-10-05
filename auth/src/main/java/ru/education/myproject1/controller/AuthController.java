package ru.education.myproject1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.education.myproject1.model.MyToken;
import ru.education.myproject1.dto.UserToken;
import ru.education.myproject1.service.RSAKeyService;
import ru.education.myproject1.service.TokenService;
import ru.education.myproject1.service.UserService;


@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RSAKeyService rsaKeyService;

    @Autowired
    private UserService userService;

    @PostMapping("/token")
    public MyToken getAccessToken() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String username = authentication.getName();

        final UserToken userToken = userService.getUserForToken(username);

        final String token = tokenService.createAccessToken(userToken);
        return new MyToken(token);
    }

    @GetMapping("/public_key")
    public String getPublicKey() {
        return rsaKeyService.getPublicKey().toJSONString();
    }

}
