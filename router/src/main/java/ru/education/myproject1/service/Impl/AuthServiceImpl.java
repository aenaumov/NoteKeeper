package ru.education.myproject1.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;
import ru.education.myproject1.client.AuthClientWebClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.education.myproject1.client.AuthUserWebClient;
import ru.education.myproject1.client.TokenWebClient;
import ru.education.myproject1.dto.ClientTokenDto;
import ru.education.myproject1.dto.UserTokenDto;
import ru.education.myproject1.service.AuthService;

import java.util.Base64;

@Slf4j
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthClientWebClient authClientWebClient;
    private final AuthUserWebClient authUserWebClient;
    private final TokenWebClient tokenWebClient;

    @Override
    public Mono<String> login(final String authHeader, final Mono<MultiValueMap<String, String>> formData) {

        final Mono<UserTokenDto> user = formData
                .flatMap(f -> loginUser(convert(f)));

//        user = user.doOnNext(u -> log.debug("USER " + u.getId()));

        final Mono<ClientTokenDto> client = loginClient(authHeader);
//        client.subscribe(c -> log.debug("Client " + c.getUsername()));

        return tokenWebClient.getToken(user);
    }

    @Override
    public Mono<String> refreshToken(final String authHeader, final Mono<UserTokenDto> user) {

        final Mono<ClientTokenDto> client = loginClient(authHeader);

//        client.subscribe(c -> log.debug("Client " + c.getUsername()));

        return tokenWebClient.getToken(user);
    }

    private Mono<UserTokenDto> loginUser(final String authHeader) {
        return authUserWebClient.Login(authHeader);
    }

    private Mono<ClientTokenDto> loginClient(final String authHeader) {
        return authClientWebClient.Login(authHeader);
    }


    private String convert(MultiValueMap<String, String> form) {
        final String data = form.getFirst("username") + ":" + form.getFirst("password");
        return "Basic " + Base64.getEncoder().encodeToString(data.getBytes());
    }

}
