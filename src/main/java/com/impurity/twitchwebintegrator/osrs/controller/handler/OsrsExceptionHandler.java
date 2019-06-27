package com.impurity.twitchwebintegrator.osrs.controller.handler;

import com.impurity.twitchwebintegrator.domain.ApiError;
import com.impurity.twitchwebintegrator.osrs.exception.OsrsClientItemHttpRequestException;
import com.impurity.twitchwebintegrator.osrs.exception.OsrsClientPlayerHttpRequestException;
import com.impurity.twitchwebintegrator.osrs.exception.OsrsItemNotFoundException;
import com.impurity.twitchwebintegrator.osrs.exception.OsrsPlayerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author tmk2003
 */
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class OsrsExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(OsrsPlayerNotFoundException.class)
    protected ResponseEntity<ApiError> handledOsrsPlayerNotFoundException(final OsrsPlayerNotFoundException ex) {
        log.info("The Osrs player from the Osrs API could not be found: {}", ex.getMessage());
        ApiError apiError = new ApiError(NOT_FOUND, "Could not find osrs player.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    
    @ExceptionHandler(OsrsItemNotFoundException.class)
    protected ResponseEntity<ApiError> handledOsrsItemNotFoundException(final OsrsItemNotFoundException ex) {
        log.info("The Osrs player from the Osrs API could not be found: {}", ex.getMessage());
        ApiError apiError = new ApiError(NOT_FOUND, "Could not find osrs item.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(OsrsClientPlayerHttpRequestException.class)
    protected ResponseEntity<ApiError> handledOsrsClientPlayerHttpRequestException(final OsrsClientPlayerHttpRequestException ex) {
        log.info("The Osrs Client was unable to successfully complete the get player request: {}:{}", ex.getStatus(), ex.getMessage());
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, "Could not complete request to fetch osrs player.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(OsrsClientItemHttpRequestException.class)
    protected ResponseEntity<ApiError> handledOsrsClientItemHttpRequestException(final OsrsClientItemHttpRequestException ex) {
        log.info("The Osrs Client was unable to successfully complete the get item request: {}:{}", ex.getStatus(), ex.getMessage());
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, "Could not complete request to fetch osrs item.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
