package ru.education.myproject1.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.education.myproject1.model.dto.UserCreateDto;
import ru.education.myproject1.model.dto.UserDto;

public interface UserService extends UserDetailsService {

    UserDto createUser (UserCreateDto userCreateDto);

}
