package ru.education.myproject1.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleException(final WebClientResponseException e) {
        log.info("{} {}", e.getStatusCode(), e.getMessage());
        return new ResponseEntity<>(
                e.getMessage(),
                e.getStatusCode());
    }
}
