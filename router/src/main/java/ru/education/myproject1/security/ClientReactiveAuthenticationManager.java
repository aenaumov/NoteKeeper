package ru.education.myproject1.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.education.myproject1.client.AuthClientWebClient;

import java.util.Base64;

@Slf4j
@Component
public class ClientReactiveAuthenticationManager implements ReactiveAuthenticationManager {
    @Autowired
    private AuthClientWebClient authClientWebClient;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        final String data = authentication.getName() + ":" + authentication.getCredentials();
        final String authHeader = "Basic " + Base64.getEncoder().encodeToString(data.getBytes());

        return authClientWebClient.authenticationAtClientServer(authHeader)
                .onErrorResume(ex -> Mono.error(new BadCredentialsException("Invalid Credentials")))
                .map(response ->
                        UsernamePasswordAuthenticationToken
                                .authenticated(authentication.getPrincipal(), "",
                                        AuthorityUtils.createAuthorityList()
                                ));
    }

}

