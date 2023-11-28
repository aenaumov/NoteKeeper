package ru.education.myproject1.repo;

import reactor.core.publisher.Mono;
import ru.education.myproject1.model.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UserReactiveRepository extends R2dbcRepository<User, Long> {

    Mono<User> getUserByUsername(String username);
}
