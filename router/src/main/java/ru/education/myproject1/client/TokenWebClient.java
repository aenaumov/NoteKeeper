package ru.education.myproject1.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
                .contentType(MediaType.APPLICATION_JSON)
                .body(loginUser, UserTokenDto.class)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(String.class);
                    } else if (response.statusCode().is4xxClientError()) {
                        return Mono.just("Error response " + response.statusCode());
                    } else {
                        return response.createException()
                                .flatMap(Mono::error);
                    }
                });
    }

    public String getPublicKeyAccessToken() {
        return getPublicKey(GET_PUBLIC_KEY_ACCESS_TOKEN_URL_TEMPLATE);
    }

    public String getPublicKeyRefreshToken() {
        return getPublicKey(GET_PUBLIC_KEY_REFRESH_TOKEN_URL_TEMPLATE);
    }

    private String getPublicKey(String getPublicKeyUrlTemplate) {
        log.debug("try send request to " +  getPublicKeyUrlTemplate);
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(getPublicKeyUrlTemplate)
                        .build())
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        log.debug("receive response 200 from " +  getPublicKeyUrlTemplate);
                        return response.bodyToMono(String.class);
                    } else if (response.statusCode().is4xxClientError()) {
                        return Mono.just("Error response");
                    } else {
                        return response.createException()
                                .flatMap(Mono::error);
                    }
                })
                .block();
    }

}
