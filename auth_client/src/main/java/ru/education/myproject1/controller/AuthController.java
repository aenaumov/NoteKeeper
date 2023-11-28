package ru.education.myproject1.controller;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.education.myproject1.dto.UserTokenDto;
import ru.education.myproject1.service.UserService;

import java.security.Principal;


@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Mono<UserTokenDto> login(Mono<Principal> principal, Authentication authentication) {
//@AuthenticationPrincipal Principal principal

        log.info(authentication.isAuthenticated()? "__TRUE__" : "__FALSE__");

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        Object obj = authentication.getPrincipal();

//        Mono<String> publisher =
//        principal
//                .map(Principal::getName)
//                .log()
////                        .subscribe(s -> log.debug("PRINCIPAL NAME " + s));
////                .subscribe();
//                .subscribe(this::print);
//                .subscribe(new Subscriber<>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        s.request(Long.MAX_VALUE);
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        log.debug("PRINCIPAL NAME " + s);
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });


//        log.debug("PRINCIPAL NAME " + str);


        final Mono<UserTokenDto> userTokenDtoMono = principal
                .map(Principal::getName)
//                .log()
                .flatMap(username -> userService.getUserForToken(username));

//        System.out.println("CLIENT " + userTokenDtoMono.block().getUsername());


// TODO
//        return Mono.just(true);
        return userTokenDtoMono;
    }

//    void print(String str) {
//        log.debug("PRINCIPAL NAME " + str);
//    }


}
