package ru.education.myproject1.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.education.myproject1.client.AuthWebClient;

import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/jwt/get").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/**").hasAuthority("SCOPE_user:read")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder(AuthWebClient authWebClient) throws JOSEException, ParseException {
        RSAPublicKey rsaPublicKey = RSAKey.parse(authWebClient.getPublicKey().block()).toRSAPublicKey();

        return NimbusJwtDecoder
                .withPublicKey(rsaPublicKey)
                .build();
    }


}
