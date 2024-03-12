package ru.education.myproject1.util;

import reactor.core.publisher.Mono;
import ru.education.myproject1.model.User;
import ru.education.myproject1.dto.UserTokenDto;

public final class UserMapper {

    private UserMapper() {

    }

    public static Mono<UserTokenDto> toUserToken(Mono<User> user) {
        return user
                .map(u -> new UserTokenDto(u.getId(), u.getUserRole())
        );
    }

}