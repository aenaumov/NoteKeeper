package ru.education.myproject1.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * WebClient
 */
@Slf4j
@Component
public class ResourceWebClient {

    private static final String GET_ALL_NOTES_OF_USER_URL_TEMPLATE = "/notes/user/";
    private final WebClient webClient;

    public ResourceWebClient(@Value("${resource-server.url}") String server_url) {
        this.webClient = WebClient.builder().baseUrl(server_url).build();
    }

    public Flux<String> getAllUserNotes(Long id, String user_id, String role) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(GET_ALL_NOTES_OF_USER_URL_TEMPLATE + id)
                        .queryParam("user_id", user_id)
                        .queryParam("role", role)
                        .build())
                .retrieve()
                .bodyToFlux(String.class);
    }


}
