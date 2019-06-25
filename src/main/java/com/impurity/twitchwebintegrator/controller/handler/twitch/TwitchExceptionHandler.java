package com.impurity.twitchwebintegrator.controller.handler.twitch;

import com.impurity.twitchwebintegrator.domain.ApiError;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchFollowersNotFoundException;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchStreamNotFoundException;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchUserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author tmk2003
 */
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TwitchExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TwitchFollowersNotFoundException.class)
    protected ResponseEntity<ApiError> handledTwitchFollowerNotFoundException(final TwitchFollowersNotFoundException ex) {
        log.info("The Twitch recent followers was not found: {}", ex.getMessage());
        ApiError apiError = new ApiError(NOT_FOUND, "Could not find twitch followers.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(TwitchUserNotFoundException.class)
    protected ResponseEntity<ApiError> handledTwitchUserNotFoundException(final TwitchUserNotFoundException ex) {
        log.info("The Twitch user was not found: {}", ex.getMessage());
        ApiError apiError = new ApiError(NOT_FOUND, "Could not find twitch user.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(TwitchStreamNotFoundException.class)
    protected ResponseEntity<ApiError> handledTwitchStreamNotFoundException(final TwitchStreamNotFoundException ex) {
        log.info("The Twitch stream was not found: {}", ex.getMessage());
        ApiError apiError = new ApiError(NOT_FOUND, "Could not find twitch stream.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
