package ru.education.myproject1.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.education.myproject1.dto.UserToken;

public interface UserService extends UserDetailsService {

    UserToken getUserForToken(String username);

}
