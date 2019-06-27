package com.impurity.twitchwebintegrator.steam.controller.handler;

import com.impurity.twitchwebintegrator.domain.ApiError;
import com.impurity.twitchwebintegrator.steam.exception.SteamClientLibraryHttpRequestException;
import com.impurity.twitchwebintegrator.steam.exception.SteamLibraryAmountNotFoundException;
import com.impurity.twitchwebintegrator.steam.exception.SteamLibraryNotFoundException;
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
public class SteamExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SteamLibraryAmountNotFoundException.class)
    protected ResponseEntity<ApiError> handledSteamLibraryAmountNotFoundException(final SteamLibraryAmountNotFoundException ex) {
        log.info("The Steam Library from the Steam API could not be found: {}", ex.getMessage());
        ApiError apiError = new ApiError(NOT_FOUND, "Could not find steam library total.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(SteamLibraryNotFoundException.class)
    protected ResponseEntity<ApiError> handledSteamLibraryNotFoundException(final SteamLibraryNotFoundException ex) {
        log.info("The Steam Library was not found: {}", ex.getMessage());
        ApiError apiError = new ApiError(NOT_FOUND, "Could not find steam library.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(SteamClientLibraryHttpRequestException.class)
    protected ResponseEntity<ApiError> handledSteamClientLibraryRequestException(final SteamClientLibraryHttpRequestException ex) {
        log.info("The Steam Client was unable to successfully complete the get library request: {}:{}", ex.getStatus(), ex.getMessage());
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, "Could not complete request to fetch steam library.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
