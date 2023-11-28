package ru.education.myproject1.repo;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;
import ru.education.myproject1.model.User;

public interface UserReactiveRepository extends R2dbcRepository<User, Long> {
    Mono<User> findUserByUsername(String username);
}
