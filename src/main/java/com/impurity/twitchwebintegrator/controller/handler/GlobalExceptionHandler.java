package com.impurity.twitchwebintegrator.controller.handler;

import com.impurity.twitchwebintegrator.exception.RestTemplateServerException;
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
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handledIllegalArgumentException(Exception ex, WebRequest request) {
        log.info("Illegal Argument: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request
        );
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<Object> handledNullPointerException(Exception ex, WebRequest request) {
        log.info("Null pointer: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request
        );
    }

    @ExceptionHandler(RestTemplateServerException.class)
    protected ResponseEntity<Object> handledRestTemplateServerException(Exception ex, WebRequest request) {
        log.info("Null pointer: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, ex.getMessage(), new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request
        );
    }

}
