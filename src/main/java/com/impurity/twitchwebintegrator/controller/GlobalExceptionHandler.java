package com.impurity.twitchwebintegrator.controller;

import com.impurity.twitchwebintegrator.exception.twitch.TwitchUserException;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchUserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Tyler Kokoszka
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handle a user not found in twitch
     *
     * @param ex - Exception that has arose
     * @param request - The request that caused this
     * @return A 404
     */
    @ExceptionHandler(TwitchUserNotFoundException.class)
    protected ResponseEntity<Object> handledTwitchUserNotFoundException(Exception ex, WebRequest request) {
        String message = ex.getMessage();
        LOGGER.info(message, ex);
        return handleExceptionInternal(
                ex, message, new HttpHeaders(), HttpStatus.NOT_FOUND, request
        );
    }

    /**
     * Handle any general issues when grabbing a twitch user
     *
     * @param ex - Exception that has arose
     * @param request - The request that caused this
     * @return A 500
     */
    @ExceptionHandler(TwitchUserException.class)
    protected ResponseEntity<Object> handledTwitchUserException(Exception ex, WebRequest request) {
        String message = "The Twitch User from the Twitch API could not be constructed into the Integrators object properly";
        LOGGER.info(message,
                ex);
        return handleExceptionInternal(
                ex, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request
        );
    }
}
