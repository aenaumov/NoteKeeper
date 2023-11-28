package ru.education.myproject1.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.UserTokenDto;
import ru.education.myproject1.service.AuthService;
import ru.education.myproject1.valid.JwtRefreshService;

import java.util.Map;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtRefreshService jwtRefreshService;

    @PostMapping("/login")
    public Mono<String> login(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
//                              @RequestBody MultiValueMap<String, String> formData,
                              @RequestParam String grant_type,
                              @RequestParam String username,
                              @RequestParam String password
    ) {

        log.debug("HEADER " + authHeader);
        log.debug("USERNAME " + username);
        log.debug("PASSWORD " + password);

        return authService.login(authHeader, username, password);
    }

    @PostMapping("/refresh")
    public Mono<String> getAccessToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                       @RequestParam String grant_type,
                                       @RequestParam String refresh_token) {

        Jwt jwt = jwtRefreshService.validateRefreshToken(refresh_token);
        Map<String, Object> claims = jwtRefreshService.getClaimsOfRefreshToken(jwt);

        Mono<UserTokenDto> userTokenDtoMono = claimsToClientTokenDto(claims);

        return authService.refreshToken(authHeader, userTokenDtoMono);
    }

    /*private String convertFormDataToAuthHeader(final String username, final String password){
        final String data = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(data.getBytes());
    }*/

    private Mono<UserTokenDto> claimsToClientTokenDto(final Map<String, Object> claims) {
        final UserTokenDto dto = new UserTokenDto(
                Long.parseLong((String) claims.get("sub")),
                (String) claims.get("role"));
        return Mono.just(dto);
    }
}
