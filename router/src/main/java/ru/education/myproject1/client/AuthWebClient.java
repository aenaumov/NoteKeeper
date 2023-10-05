package ru.education.myproject1.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * WebClient
 */
@Slf4j
@Component
public class AuthWebClient {

    private static final String GET_TOKEN_URL_TEMPLATE = "/auth/token";
    private static final String GET_PUBLIC_KEY_URL_TEMPLATE = "/auth/public_key";
    private final WebClient webClient;

    public AuthWebClient(@Value("${auth-server.url}") String server_url) {
        this.webClient = WebClient.builder().baseUrl(server_url).build();
    }


        public Mono<String> getAccessToken(String authHeader) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(GET_TOKEN_URL_TEMPLATE)
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
