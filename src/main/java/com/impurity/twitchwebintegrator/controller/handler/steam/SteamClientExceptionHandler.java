package com.impurity.twitchwebintegrator.controller.handler.steam;

import com.impurity.twitchwebintegrator.domain.ApiError;
import com.impurity.twitchwebintegrator.exception.steam.SteamClientLibraryHttpRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author tmk2003
 */
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SteamClientExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SteamClientLibraryHttpRequestException.class)
    protected ResponseEntity<ApiError> handledSteamClientLibraryRequestException(final SteamClientLibraryHttpRequestException ex) {
        log.info("The Steam Client was unable to successfully complete the get library request: {}:{}", ex.getStatus(), ex.getMessage());
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, "Could not complete request to fetch steam library.", ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
