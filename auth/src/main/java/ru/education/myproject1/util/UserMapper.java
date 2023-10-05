package ru.education.myproject1.util;

import ru.education.myproject1.model.User;
import ru.education.myproject1.dto.UserToken;

public final class UserMapper {

    private UserMapper() {

    }

    public static UserToken toUserToken(User user) {
        return new UserToken(
                user.getId(),
                user.getUserRole());
    }

}
