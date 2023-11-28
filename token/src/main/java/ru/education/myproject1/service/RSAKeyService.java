package ru.education.myproject1.service;

import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.jwk.RSAKey;

public interface RSAKeyService {

    RSAKey getPublicKeyAccessToken();

    JWSSigner getSignerAccessToken();

    RSAKey getPublicKeyRefreshToken();

    JWSSigner getSignerRefreshToken();
}
