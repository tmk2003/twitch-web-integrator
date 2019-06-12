package com.impurity.twitchwebintegrator.controller.handler.twitch;

import com.impurity.twitchwebintegrator.exception.twitch.TwitchFollowerException;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchFollowerNotFoundException;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchUserException;
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
public class TwitchFollowersExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TwitchFollowerException.class)
    protected ResponseEntity<Object> handledTwitchFollowerException(Exception ex, WebRequest request) {
        log.info("The Twitch Followers from the Twitch API could not be constructed: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Error creating the Followers", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request
        );
    }

    @ExceptionHandler(TwitchFollowerNotFoundException.class)
    protected ResponseEntity<Object> handledTwitchFollowerNotFoundException(Exception ex, WebRequest request) {
        log.info("The Twitch Followers was not found: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Twitch Followers not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request
        );
    }
}
