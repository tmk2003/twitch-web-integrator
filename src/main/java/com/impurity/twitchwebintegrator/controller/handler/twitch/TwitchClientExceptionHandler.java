package com.impurity.twitchwebintegrator.controller.handler.twitch;

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

/**
 * @author tmk2003
 */
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TwitchClientExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TwitchClientFollowersRequestException.class)
    protected ResponseEntity<Object> handledTwitchClientFollowersRequestException(Exception ex, WebRequest request) {
        log.info("The Twitch client had an issue while retrieving recent followers : {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Twitch client failed to get recent followers", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request
        );
    }

    @ExceptionHandler(TwitchClientStreamRequestException.class)
    protected ResponseEntity<Object> handledTwitchClientStreamRequestException(Exception ex, WebRequest request) {
        log.info("The Twitch client had an issue while retrieving stream : {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Twitch client failed to get stream", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request
        );
    }

    @ExceptionHandler(TwitchClientUserRequestException.class)
    protected ResponseEntity<Object> handledTwitchClientUserRequestException(Exception ex, WebRequest request) {
        log.info("The Twitch client had an issue while retrieving users : {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Twitch client failed to get users", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request
        );
    }
}
