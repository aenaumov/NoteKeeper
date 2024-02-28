package ru.education.myproject1.service.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import org.springframework.stereotype.Service;
import ru.education.myproject1.service.RSAKeyService;

@Service
public class RSAKeyServiceImpl implements RSAKeyService {
    private final int KEY_LENGTH = 2048;
    private final RSAKey rsaKeyAccessToken;
    private final RSAKey rsaKeyRefreshToken;

    public RSAKeyServiceImpl() {
        this.rsaKeyRefreshToken = rsaKeyPairGenerator();
        this.rsaKeyAccessToken = rsaKeyPairGenerator();
    }

    private RSAKey rsaKeyPairGenerator() {
        try {
            return new RSAKeyGenerator(KEY_LENGTH)
                    .keyID("123")
                    .generate();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RSAKey getPublicKeyAccessToken() {
        return rsaKeyAccessToken.toPublicJWK();
    }

    @Override
    public JWSSigner getSignerAccessToken() {
        try {
            return new RSASSASigner(rsaKeyAccessToken);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RSAKey getPublicKeyRefreshToken() {
        return rsaKeyRefreshToken.toPublicJWK();
    }

    @Override
    public JWSSigner getSignerRefreshToken() {
        try {
            return new RSASSASigner(rsaKeyRefreshToken);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
