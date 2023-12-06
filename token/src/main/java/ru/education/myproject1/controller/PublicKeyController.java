package ru.education.myproject1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.education.myproject1.service.RSAKeyService;


@Slf4j
@RestController
@RequestMapping("auth/public_key")
public class PublicKeyController {

    @Autowired
    private RSAKeyService rsaKeyService;

    @GetMapping("/access")
    public String getPublicKeyAccessToken() {
        log.debug("send public key for access token");
        return rsaKeyService.getPublicKeyAccessToken().toJSONString();
    }

    @GetMapping("/refresh")
    public String getPublicKeyRefreshToken() {
        log.debug("send public key for refresh token");
        return rsaKeyService.getPublicKeyRefreshToken().toJSONString();
    }

}
