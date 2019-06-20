package com.impurity.twitchwebintegrator.controller.handler.twitch;

import com.impurity.twitchwebintegrator.exception.twitch.TwitchRecentFollowersNotFoundException;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchStreamNotFoundException;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchTotalFollowersNotFoundException;
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
public class TwitchExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TwitchRecentFollowersNotFoundException.class)
    protected ResponseEntity<Object> handledTwitchFollowerNotFoundException(Exception ex, WebRequest request) {
        log.info("The Twitch recent followers was not found: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Twitch recent followers not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request
        );
    }

    @ExceptionHandler(TwitchTotalFollowersNotFoundException.class)
    protected ResponseEntity<Object> handledTwitchTotalFollowersNotFoundException(Exception ex, WebRequest request) {
        log.info("The Twitch total followers was not found: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Twitch total followers not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request
        );
    }

    @ExceptionHandler(TwitchUserNotFoundException.class)
    protected ResponseEntity<Object> handledTwitchUserNotFoundException(Exception ex, WebRequest request) {
        log.info("The Twitch user was not found: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Twitch user not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request
        );
    }

    @ExceptionHandler(TwitchStreamNotFoundException.class)
    protected ResponseEntity<Object> handledTwitchStreamNotFoundException(Exception ex, WebRequest request) {
        log.info("The Twitch stream was not found: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Twitch stream not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request
        );
    }
}
