package ru.education.myproject1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    @Qualifier("accessTokenJwtDecoder")
    private ReactiveJwtDecoder accessTokenJwtDecoder;

    @Autowired
    private ClientReactiveAuthenticationManager authenticationManager;

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    public SecurityWebFilterChain filterChainHttpBasic(ServerHttpSecurity http) {

        http
                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/auth/**"))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                // TODO sessionManagement
                .authorizeExchange(authorize -> authorize
                        .anyExchange().authenticated())
                .httpBasic(httpBasicSpec -> httpBasicSpec.authenticationManager(authenticationManager)
                );

        return http.build();
    }


    @Bean
    public SecurityWebFilterChain filterChainJWT(ServerHttpSecurity http) {

        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

// TODO sessionManagement
                .authorizeExchange(authorize -> authorize

                        .pathMatchers(HttpMethod.POST, "/error")
                        .permitAll()
                        .anyExchange().authenticated())

                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwtSpec -> jwtSpec.jwtDecoder(accessTokenJwtDecoder)));

        return http.build();
    }

}
