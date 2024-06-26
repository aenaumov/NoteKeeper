package ru.education.myproject1.service.Impl;

import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.UserTokenDto;
import ru.education.myproject1.model.User;
import ru.education.myproject1.repo.UserReactiveRepository;
import ru.education.myproject1.service.UserService;
import ru.education.myproject1.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserReactiveRepository userReactiveRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) throws UsernameNotFoundException {

        return this.convert(
                this.userReactiveRepository.getUserByUsername(username)
                        .switchIfEmpty(Mono.error(new UsernameNotFoundException("User ‘" + username + "’ not found")))
        );
    }

    @Override
    public Mono<UserTokenDto> getUserForToken(String username) {
        Mono<User> user = userReactiveRepository.getUserByUsername(username);
        return UserMapper.toUserToken(user);
    }

    private Mono<UserDetails> convert(Mono<User> userMono) {
        return userMono.map(m -> org.springframework.security.core.userdetails.User.builder()
                .username(m.getUsername())
                .password(m.getPassword())
                .disabled(false)
                .accountExpired(false)
                .accountLocked(false)
                .roles(m.getUserRole())
                .build()
        );
    }
}
