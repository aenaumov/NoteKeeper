package ru.education.myproject1.service;

import ru.education.myproject1.model.dto.UserCreateDto;
import ru.education.myproject1.model.dto.UserDto;

public interface UserService {

    UserDto createUser (UserCreateDto userCreateDto);

}
