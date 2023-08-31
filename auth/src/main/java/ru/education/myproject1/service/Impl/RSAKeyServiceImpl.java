package ru.education.myproject1.service.Impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import org.springframework.stereotype.Service;
import ru.education.myproject1.service.RSAKeyService;

@Service
public class RSAKeyServiceImpl implements RSAKeyService {

    private final RSAKey rsaKey;

    private RSAKey rsaKeyPairGenerator() {
        try {
            return new RSAKeyGenerator(2048)
                    .keyID("123")
                    .generate();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public RSAKeyServiceImpl() {
        this.rsaKey = rsaKeyPairGenerator();
    }

    @Override
    public RSAKey getPublicKey() {
        return rsaKey.toPublicJWK();
    }

    @Override
    public JWSSigner getSigner() {
        try {
            return new RSASSASigner(rsaKey);
    } catch (JOSEException e) {
        throw new RuntimeException(e);
    }
    }

}
