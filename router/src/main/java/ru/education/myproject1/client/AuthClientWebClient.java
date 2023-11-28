package ru.education.myproject1.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.ClientTokenDto;
import ru.education.myproject1.dto.UserTokenDto;

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
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//                .bodyValue(loginUser)
//                .body(loginUser, UserTokenDto.class)
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
