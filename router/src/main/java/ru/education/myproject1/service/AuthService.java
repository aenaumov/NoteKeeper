package ru.education.myproject1.service;

import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<String> getAccessToken(String authHeader);

}
