package ru.education.myproject1.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.ClientTokenDto;

/**
 * WebClient
 */
@Slf4j
@Component
public class AuthClientWebClient {

    private static final String LOGIN_URL_TEMPLATE = "/auth/login";
    private static final String ACCESS_TOKEN_URL_TEMPLATE = "/auth/token/access";
    private final WebClient webClient;

    public AuthClientWebClient(@Value("${auth-client-server.url}") String server_url) {
        this.webClient = WebClient.builder().baseUrl(server_url).build();
    }

    public Mono<ClientTokenDto> Login(String authHeader) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(LOGIN_URL_TEMPLATE)
                        .build())
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ClientTokenDto.class);
    }

    public Mono<String> getAccessToken(String authHeader) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(ACCESS_TOKEN_URL_TEMPLATE)
                        .build())
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .bodyToMono(String.class);
    }
}
