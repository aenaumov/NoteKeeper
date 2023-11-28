package ru.education.myproject1.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import ru.education.myproject1.client.TokenWebClient;

import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

@Configuration
public class JwtDecoderConfig {

    @Autowired
    TokenWebClient tokenWebClient;

    @Bean(name = "accessTokenJwtDecoder")
    public JwtDecoder accessTokenJwtDecoder() throws JOSEException, ParseException {
        final RSAPublicKey rsaPublicKey = RSAKey.parse(tokenWebClient.getPublicKeyAccessToken().block()).toRSAPublicKey();
        return NimbusJwtDecoder
                .withPublicKey(rsaPublicKey)
                .build();
    }

    @Bean(name = "refreshTokenJwtDecoder")
    public JwtDecoder refreshTokenJwtDecoder() throws JOSEException, ParseException {
        final RSAPublicKey rsaPublicKey = RSAKey.parse(tokenWebClient.getPublicKeyRefreshToken().block()).toRSAPublicKey();
        return NimbusJwtDecoder
                .withPublicKey(rsaPublicKey)
                .build();
    }

}
