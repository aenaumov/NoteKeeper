package ru.education.myproject1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    @Qualifier("accessTokenJwtDecoder")
    private ReactiveJwtDecoder accessTokenJwtDecoder;

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {

        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

// TODO sessionManagement
                .authorizeExchange(authorize -> authorize

                                .pathMatchers(HttpMethod.POST,"/auth/**", "/error")
                        .permitAll()
                        .anyExchange().authenticated())

                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwtSpec -> jwtSpec.jwtDecoder(accessTokenJwtDecoder)));

        return http.build();
    }

}
