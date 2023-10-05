package ru.education.myproject1.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.education.myproject1.model.User;
import ru.education.myproject1.dto.UserToken;
import ru.education.myproject1.repo.UserRepository;
import ru.education.myproject1.service.UserService;
import ru.education.myproject1.util.UserMapper;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User ‘" + username + "’ not found");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .disabled(false)
                .accountExpired(false)
                .accountLocked(false)
                .roles(user.getUserRole())
                .build();
    }

    @Override
    public UserToken getUserForToken(String username) {
        User user = userRepository.findUserByUsername(username);
        return UserMapper.toUserToken(user);
    }
}
