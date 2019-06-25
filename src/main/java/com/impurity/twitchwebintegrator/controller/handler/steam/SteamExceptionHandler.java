package com.impurity.twitchwebintegrator.controller.handler.steam;

import com.impurity.twitchwebintegrator.domain.ApiError;
import com.impurity.twitchwebintegrator.exception.steam.SteamLibraryAmountNotFoundException;
import com.impurity.twitchwebintegrator.exception.steam.SteamLibraryNotFoundException;
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
public class SteamExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SteamLibraryAmountNotFoundException.class)
    protected ResponseEntity<ApiError> handledSteamLibraryAmountNotFoundException(final SteamLibraryAmountNotFoundException ex) {
        log.info("The Steam Library from the Steam API could not be found: {}", ex.getMessage());
        ApiError apiError = new ApiError(NOT_FOUND, "Could not find steam library amount.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(SteamLibraryNotFoundException.class)
    protected ResponseEntity<ApiError> handledSteamLibraryNotFoundException(final SteamLibraryNotFoundException ex) {
        log.info("The Steam Library was not found: {}", ex.getMessage());
        ApiError apiError = new ApiError(NOT_FOUND, "Could not find steam library.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
