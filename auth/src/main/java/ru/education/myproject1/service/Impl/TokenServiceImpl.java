package ru.education.myproject1.service.Impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;
import ru.education.myproject1.service.RSAKeyService;
import ru.education.myproject1.service.TokenService;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    private final Long TOKEN_LIFETIME = 30 * 60L * 60 * 1000; // 30 min

    private final RSAKeyService rsaKeyService;

    public TokenServiceImpl(RSAKeyService rsaKeyService) {
        this.rsaKeyService = rsaKeyService;
    }

    @Override
    public String createJWT() {
        RSAKey rsaPublicJWK = rsaKeyService.getPublicKey();
        JWSSigner signer = rsaKeyService.getSigner();

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .claim("scope", "user:read")
                .claim("role", "USER")
                .subject("test")
                .issuer("edu-project")
                .expirationTime(new Date(new Date().getTime() + TOKEN_LIFETIME))
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaPublicJWK.getKeyID()).build(),
                claimsSet);

        try {
            signedJWT.sign(signer);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return signedJWT.serialize();
    }

}
