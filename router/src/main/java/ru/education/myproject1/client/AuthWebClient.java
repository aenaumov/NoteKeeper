package ru.education.myproject1.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * WebClient
 */
@Slf4j
@Component
public class AuthWebClient {

    private static final String AUTH_WEB_SERVER = "http://localhost:9090";
    private static final String GET_JWT_URL_TEMPLATE = "/auth/jwt/get";
    private static final String GET_PUBLIC_KEY_URL_TEMPLATE = "/auth/jwt/key";
    private final WebClient webClient;

    public AuthWebClient() {
        this.webClient = WebClient.builder().baseUrl(AUTH_WEB_SERVER).build();
    }


    public Mono<String> getJwt(String authHeader) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(GET_JWT_URL_TEMPLATE)
                        .build())
                .header("Authorization", authHeader)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getPublicKey() {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(GET_PUBLIC_KEY_URL_TEMPLATE)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

}
