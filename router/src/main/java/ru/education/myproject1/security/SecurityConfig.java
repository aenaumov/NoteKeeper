package ru.education.myproject1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    @Qualifier("accessTokenJwtDecoder")
    private JwtDecoder accessTokenJwtDecoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/auth/**", "/error").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/user/**").hasAuthority("SCOPE_user:read")

                                .anyRequest().authenticated())
//                .addFilter(TestFilter())
//                .addFilterBefore(refreshTokenFilter, BearerTokenAuthenticationFilter.class)
                .oauth2ResourceServer(oauth2 -> oauth2
                                .jwt(jwt -> jwt.decoder(accessTokenJwtDecoder))
//                        .jwt(Customizer.withDefaults())
                );
        return http.build();
    }

}
