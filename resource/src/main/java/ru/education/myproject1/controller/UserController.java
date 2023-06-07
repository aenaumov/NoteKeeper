package ru.education.myproject1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.education.myproject1.model.dto.UserCreateDto;
import ru.education.myproject1.model.dto.UserDto;
import ru.education.myproject1.service.UserService;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDto postUser(@RequestBody UserCreateDto userCreateDto) {
        log.info("___ POST user {}", userCreateDto);
        return userService.createUser(userCreateDto);
    }
}
