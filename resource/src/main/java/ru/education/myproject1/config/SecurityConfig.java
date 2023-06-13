package ru.education.myproject1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                // Spring Security should completely ignore URLs starting with /resources/
                .requestMatchers("/*");
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
//                                .requestMatchers("/user/details")
//                                .hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/", "/**", "/*/*")
                                .permitAll());
                /*.formLogin((form) ->
                        form
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .loginPage("/login")
                                .permitAll()
                                .defaultSuccessUrl("/user/details", true)
                                .failureHandler(authenticationFailureHandler))*/
                /*.logout((logout) ->
                        logout
                                .permitAll()
                                .logoutSuccessUrl("/index")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                );*/
        return http.build();

    }
}
