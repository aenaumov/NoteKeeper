package ru.education.myproject1.util;


import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.UserTokenDto;
import ru.education.myproject1.model.User;

public final class UserMapper {

    private UserMapper() {

    }

    public static Mono<UserTokenDto> toUserToken(Mono<User> user) {
        return user.flatMap(
                u -> Mono.just(
                        new UserTokenDto(u.getId(), u.getUserRole())
                )
        );

    }

}
