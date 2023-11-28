package ru.education.myproject1.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.jwt.JwtDecoder;
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

//    @Autowired
//    @Qualifier("refreshTokenJwtDecoder")
//    private JwtDecoder refreshTokenJwtDecoder;

    @Override
    public Mono<String> login(final String authHeader, final String username, final String password) {

        final String authUserHeader = convertFormDataToAuthHeader(username, password);

        final Mono<UserTokenDto> user = loginUser(authUserHeader);

//        TODO check
//        Mono<String> loginClient = loginClient(authHeader).doOnSubscribe((str) -> log.debug("CLIENT AUTH " + str));
//        loginClient.subscribe();

        final Mono<ClientTokenDto> client = loginClient(authHeader);
        print(client);

        return tokenWebClient.getToken(user);
    }

    @Override
    public Mono<String> refreshToken(final String authHeader, final Mono<UserTokenDto> user) {

        final Mono<ClientTokenDto> client = loginClient(authHeader);
        print(client);

        return tokenWebClient.getToken(user);
    }

    private Mono<UserTokenDto> loginUser(final String authHeader) {
        return authUserWebClient.Login(authHeader);
    }

    private Mono<ClientTokenDto> loginClient(final String authHeader) {
        /*try{
            Mono<ClientTokenDto> clientTokenDtoMono = authClientWebClient.Login(authHeader);
        }
        catch (final Throwable e){
            log.info("__error__  {}", e.getMessage(), e);
            return null;
        }*/

        return authClientWebClient.Login(authHeader);
    }

    private String convertFormDataToAuthHeader(final String username, final String password) {
        final String data = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(data.getBytes());
    }

    private void print(Mono<ClientTokenDto> client) {
        log.debug("Client " + client.block().getUsername());
    }

}
