package ru.education.myproject1.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * WebClient
 */
@Slf4j
@Component
public class AuthClientWebClient {

    private static final String AUTH_URL_TEMPLATE = "/client/auth/authentication";
    private final WebClient webClient;

    public AuthClientWebClient(@Value("${auth-client-server.url}") String server_url) {
        this.webClient = WebClient.builder().baseUrl(server_url).build();
    }

    public Mono<ResponseEntity<Boolean>> authenticationAtClientServer(String authHeader) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(AUTH_URL_TEMPLATE)
                        .build())
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .toEntity(Boolean.class);
    }

}
