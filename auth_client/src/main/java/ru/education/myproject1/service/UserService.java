package ru.education.myproject1.service;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.UserTokenDto;

public interface UserService extends ReactiveUserDetailsService {

    Mono<UserTokenDto> getUserForToken(String username);

}
