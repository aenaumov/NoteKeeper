package ru.education.myproject1.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.education.myproject1.model.User;
import ru.education.myproject1.model.dto.UserCreateDto;
import ru.education.myproject1.model.dto.UserDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

    private static final String defaultUserRole = "USER";
    private static final Boolean defaultVerification = false;

    public static User toUser(UserCreateDto userCreateDto, PasswordEncoder passwordEncoder) {
        return new User(
                null,
                userCreateDto.getUsername(),
                userCreateDto.getEmail(),
                passwordEncoder.encode(userCreateDto.getPassword()),
                defaultVerification,
                defaultUserRole);
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getUsername(),
                user.getEmail()
        );
    }
}
