package ru.education.myproject1.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.UserTokenDto;

/**
 * WebClient
 */
@Slf4j
@Component
public class TokenWebClient {

    private static final String TOKEN_ACCESS_REFRESH_URL_TEMPLATE = "/token/access-refresh";
    private static final String GET_PUBLIC_KEY_ACCESS_TOKEN_URL_TEMPLATE = "auth/public_key/access";
    private static final String GET_PUBLIC_KEY_REFRESH_TOKEN_URL_TEMPLATE = "auth/public_key/refresh";
    private final WebClient webClient;

    public TokenWebClient(@Value("${token-server.url}") String server_url) {
        this.webClient = WebClient.builder().baseUrl(server_url).build();
    }


    public Mono<String> getToken(Mono<UserTokenDto> loginUser) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(TOKEN_ACCESS_REFRESH_URL_TEMPLATE)
                        .build())
//                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .contentType(MediaType.APPLICATION_JSON)
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(loginUser, UserTokenDto.class)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getPublicKeyAccessToken() {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(GET_PUBLIC_KEY_ACCESS_TOKEN_URL_TEMPLATE)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getPublicKeyRefreshToken() {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(GET_PUBLIC_KEY_REFRESH_TOKEN_URL_TEMPLATE)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

}
