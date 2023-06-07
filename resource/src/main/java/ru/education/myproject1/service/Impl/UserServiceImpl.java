package ru.education.myproject1.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.education.myproject1.model.User;
import ru.education.myproject1.model.dto.UserCreateDto;
import ru.education.myproject1.model.dto.UserDto;
import ru.education.myproject1.repo.UserRepository;
import ru.education.myproject1.service.UserService;
import ru.education.myproject1.util.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {
        final User user = UserMapper.toUser(userCreateDto, passwordEncoder);

        userRepository.save(user);

        return UserMapper.toUserDto(user);
    }
}
