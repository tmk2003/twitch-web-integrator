package com.impurity.twitchwebintegrator.controller.handler.twitch;

import com.impurity.twitchwebintegrator.exception.twitch.TwitchStreamCreationException;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchStreamNotFoundException;
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
public class TwitchStreamExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TwitchStreamCreationException.class)
    protected ResponseEntity<Object> handledTwitchStreamCreationException(Exception ex, WebRequest request) {
        log.info("The Twitch Stream from the Twitch API could not be constructed: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Error creating the Stream", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request
        );
    }

    @ExceptionHandler(TwitchStreamNotFoundException.class)
    protected ResponseEntity<Object> handledTwitchStreamNotFoundException(Exception ex, WebRequest request) {
        log.info("The Twitch Stream was not found: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Twitch Stream not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request
        );
    }
}
