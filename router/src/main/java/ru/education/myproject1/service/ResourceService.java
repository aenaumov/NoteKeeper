package ru.education.myproject1.service;

import reactor.core.publisher.Flux;

public interface ResourceService {
    Flux<String> getUserNotes(Long id, String user_id, String role);
}
