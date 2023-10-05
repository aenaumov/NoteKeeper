package ru.education.myproject1.service.Impl;

import reactor.core.publisher.Mono;
import ru.education.myproject1.client.AuthWebClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.education.myproject1.service.AuthService;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthWebClient authWebClient;

    @Override
    public Mono<String> getAccessToken(String authHeader) {
        return authWebClient.getAccessToken(authHeader);
    }

}
