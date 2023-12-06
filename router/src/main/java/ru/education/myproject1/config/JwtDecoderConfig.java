package ru.education.myproject1.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import ru.education.myproject1.client.TokenWebClient;

import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

@Slf4j
@Configuration
public class JwtDecoderConfig {

    @Autowired
    TokenWebClient tokenWebClient;

    @Bean(name = "accessTokenJwtDecoder")
    public ReactiveJwtDecoder accessTokenJwtDecoder() throws JOSEException, ParseException {
        final RSAPublicKey rsaPublicKey = RSAKey.parse(tokenWebClient.getPublicKeyAccessToken()).toRSAPublicKey();
        return NimbusReactiveJwtDecoder
                .withPublicKey(rsaPublicKey)
                .build();
    }

    @Bean(name = "refreshTokenJwtDecoder")
    public ReactiveJwtDecoder refreshTokenJwtDecoder() throws JOSEException, ParseException {
        final RSAPublicKey rsaPublicKey = RSAKey.parse(tokenWebClient.getPublicKeyRefreshToken()).toRSAPublicKey();
        return NimbusReactiveJwtDecoder
                .withPublicKey(rsaPublicKey)
                .build();
    }

}
