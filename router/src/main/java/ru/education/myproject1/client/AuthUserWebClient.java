package ru.education.myproject1.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.UserTokenDto;


/**
 * WebClient
 */
@Slf4j
@Component
public class AuthUserWebClient {

    private static final String LOGIN_URL_TEMPLATE = "/user/auth/login";
    private final WebClient webClient;

    public AuthUserWebClient(@Value("${auth-user-server.url}") String server_url) {
        this.webClient = WebClient.builder().baseUrl(server_url).build();
    }

    public Mono<ResponseEntity<UserTokenDto>> getUserForToken(String authHeader) {

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(LOGIN_URL_TEMPLATE)
                        .build())
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .toEntity(UserTokenDto.class);
    }

}
