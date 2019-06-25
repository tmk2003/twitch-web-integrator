package com.impurity.twitchwebintegrator.controller.handler;

import com.impurity.twitchwebintegrator.domain.ApiError;
import com.impurity.twitchwebintegrator.exception.RestTemplateServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

/**
 * @author tmk2003
 */
@Slf4j
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ApiError> handledIllegalArgumentException(final IllegalArgumentException ex) {
        log.info("Illegal Argument: {}", ex.getMessage());
        ApiError apiError = new ApiError(BAD_REQUEST, "Illegal Argument present internally", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ApiError> handledNullPointerException(final NullPointerException ex) {
        log.info("Null pointer: {}", ex.getMessage());
        ApiError apiError = new ApiError(BAD_REQUEST, "Null Pointer present internally", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(RestTemplateServerException.class)
    protected ResponseEntity<ApiError> handledRestTemplateServerException(final RestTemplateServerException ex) {
        log.info("Rest template server exception: {}", ex.getMessage());
        ApiError apiError = new ApiError(SERVICE_UNAVAILABLE, "Could not complete request to external resource. Try again later.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
