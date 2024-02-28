package ru.education.myproject1.service;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<ResponseEntity<String>> getToken(final Mono<MultiValueMap<String, String>> formData);
    Mono<ResponseEntity<String>> refreshToken(final Mono<MultiValueMap<String, String>> formData);

}
