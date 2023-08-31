package ru.education.myproject1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.education.myproject1.service.RSAKeyService;
import ru.education.myproject1.service.TokenService;


@Slf4j
@RestController
@RequestMapping("/auth/jwt")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RSAKeyService rsaKeyService;

    @GetMapping("/get")
    public String getJWT() {
        return tokenService.createJWT();
    }

    @GetMapping("/key")
    public String getPublicKey() {
        return rsaKeyService.getPublicKey().toJSONString();
    }

}
