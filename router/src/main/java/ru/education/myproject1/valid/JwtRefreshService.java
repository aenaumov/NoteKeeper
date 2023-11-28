package ru.education.myproject1.valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtRefreshService {

@Autowired
    @Qualifier("refreshTokenJwtDecoder")
    private JwtDecoder refreshTokenJwtDecoder;

    public Jwt validateRefreshToken(final String token){
        return refreshTokenJwtDecoder.decode(token);
    }

    public Map<String, Object> getClaimsOfRefreshToken(Jwt jwt) {
        return jwt.getClaims();
    }
}