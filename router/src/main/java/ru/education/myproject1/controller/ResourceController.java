package ru.education.myproject1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.education.myproject1.client.ResourceWebClient;

import java.util.Map;

@Slf4j
@RestController

@RequestMapping("/notes/user")
public class ResourceController {

    @Autowired
    ResourceWebClient resourceWebClient;

    @GetMapping("/{id}")
    public Flux<String> getAllNotesOfUser(@PathVariable Long id,
                                          @AuthenticationPrincipal Jwt principal) {
        System.out.println("claims " + principal.getClaims());

        final Map<String, Object> claims = principal.getClaims();
        final String role = (String) claims.get("role");
        final String user_id = principal.getSubject();

        return resourceWebClient.getAllUserNotes(id, user_id, role);

    }
}
