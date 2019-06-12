package com.impurity.twitchwebintegrator.controller.handler.twitch;

import com.impurity.twitchwebintegrator.exception.twitch.TwitchUserCreationException;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchUserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author tmk2003
 */
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TwitchUserExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TwitchUserCreationException.class)
    protected ResponseEntity<Object> handledTwitchUserCreationException(Exception ex, WebRequest request) {
        log.info("The Twitch User from the Twitch API could not be constructed: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Error creating the user", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request
        );
    }

    @ExceptionHandler(TwitchUserNotFoundException.class)
    protected ResponseEntity<Object> handledTwitchUserNotFoundException(Exception ex, WebRequest request) {
        log.info("The Twitch User was not found: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Twitch User not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request
        );
    }
}
