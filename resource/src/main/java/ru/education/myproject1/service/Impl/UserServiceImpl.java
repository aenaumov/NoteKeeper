package ru.education.myproject1.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.education.myproject1.model.User;
import ru.education.myproject1.model.dto.UserCreateDto;
import ru.education.myproject1.model.dto.UserDto;
import ru.education.myproject1.repo.UserRepository;
import ru.education.myproject1.service.UserService;
import ru.education.myproject1.util.UserMapper;

@Service
@Transactional
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
//                .disabled(!user.getVerified())
                .disabled(false)
                .accountExpired(false)
                .accountLocked(false)
                .roles(user.getUserRole())
                .build();
    }
}
