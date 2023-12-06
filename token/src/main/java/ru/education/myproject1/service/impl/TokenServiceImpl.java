package ru.education.myproject1.service.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.TokenDto;
import ru.education.myproject1.service.RSAKeyService;
import ru.education.myproject1.service.TokenService;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    private final Long ACCESS_TOKEN_LIFETIME = 30 * 60L * 60 * 1000; // 30 min
    private final Long REFRESH_TOKEN_LIFETIME = 30L; // 30 days
    private final RSAKeyService rsaKeyService;

    public TokenServiceImpl(RSAKeyService rsaKeyService) {
        this.rsaKeyService = rsaKeyService;
    }

    @Override
    public Mono<String> createAccessToken(final TokenDto tokenDto) {
        final RSAKey rsaPublicJWK = rsaKeyService.getPublicKeyAccessToken();
        final JWSSigner signer = rsaKeyService.getSignerAccessToken();
        final JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .claim("scope", "user:read")
                .claim("role", tokenDto.getUserRole())
                .subject(tokenDto.getId().toString())
                .issuer("edu-project")
                .expirationTime(new Date(new Date().getTime() + ACCESS_TOKEN_LIFETIME))
                .build();
        final SignedJWT signedJWT = getSignedJwt(rsaPublicJWK, claimsSet, signer);
        return Mono.just(signedJWT.serialize());
    }

    @Override
    public Mono<String> createRefreshToken(TokenDto tokenDto) {
        final RSAKey rsaPublicJWK = rsaKeyService.getPublicKeyRefreshToken();
        final JWSSigner signer = rsaKeyService.getSignerRefreshToken();
        final ZonedDateTime time = ZonedDateTime.now().plusDays(REFRESH_TOKEN_LIFETIME);
        final Date expirationDate = Date.from(time.toInstant());
        final JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .claim("role", tokenDto.getUserRole())
                .subject(tokenDto.getId().toString())
                .expirationTime(expirationDate)
                .build();
        final SignedJWT signedJWT = getSignedJwt(rsaPublicJWK, claimsSet, signer);
        return Mono.just(signedJWT.serialize());
    }

    private SignedJWT getSignedJwt(final RSAKey rsaPublicJWK, final JWTClaimsSet claimsSet, final JWSSigner signer) {

        final SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaPublicJWK.getKeyID()).build(),
                claimsSet);
        try {
            signedJWT.sign(signer);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return signedJWT;
    }
}
