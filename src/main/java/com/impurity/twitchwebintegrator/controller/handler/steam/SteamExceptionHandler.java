package com.impurity.twitchwebintegrator.controller.handler.steam;

import com.impurity.twitchwebintegrator.exception.steam.SteamClientLibraryRequestException;
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

/**
 * @author tmk2003
 */
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SteamExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SteamLibraryAmountNotFoundException.class)
    protected ResponseEntity<Object> handledSteamLibraryAmountNotFoundException(Exception ex, WebRequest request) {
        log.info("The Steam Library from the Steam API could not be found: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Steam Library amount not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request
        );
    }

    @ExceptionHandler(SteamLibraryNotFoundException.class)
    protected ResponseEntity<Object> handledSteamLibraryNotFoundException(Exception ex, WebRequest request) {
        log.info("The Steam Library was not found: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Steam Library not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request
        );
    }

    @ExceptionHandler(SteamClientLibraryRequestException.class)
    protected ResponseEntity<Object> handledSteamClientLibraryRequestException(Exception ex, WebRequest request) {
        log.info("The Steam Client was unable to successfully complete the get library request: {}", ex.getMessage());
        return handleExceptionInternal(
                ex, "Get Steam Library failed", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request
        );
    }
}
