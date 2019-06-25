package com.impurity.twitchwebintegrator.controller.handler.twitch;

import com.impurity.twitchwebintegrator.domain.ApiError;
import com.impurity.twitchwebintegrator.exception.twitch.*;
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

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author tmk2003
 */
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TwitchClientExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TwitchClientFollowersHttpRequestException.class)
    protected ResponseEntity<ApiError> handledTwitchClientFollowersRequestException(final TwitchClientFollowersHttpRequestException ex) {
        log.info("The Twitch client had an issue while retrieving recent followers : {}:{}", ex.getStatus(), ex.getMessage());
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, "Could not complete request to fetch twitch followers.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(TwitchClientStreamHttpRequestException.class)
    protected ResponseEntity<ApiError> handledTwitchClientStreamRequestException(final TwitchClientStreamHttpRequestException ex) {
        log.info("The Twitch client had an issue while retrieving stream: {}:{}", ex.getStatus(), ex.getMessage());
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, "Could not complete request to fetch twitch stream.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(TwitchClientUserHttpRequestException.class)
    protected ResponseEntity<ApiError> handledTwitchClientUserRequestException(final TwitchClientUserHttpRequestException ex) {
        log.info("The Twitch client had an issue while retrieving users : {}:{}", ex.getStatus(), ex.getMessage());
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, "Could not complete request to fetch twitch user.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
