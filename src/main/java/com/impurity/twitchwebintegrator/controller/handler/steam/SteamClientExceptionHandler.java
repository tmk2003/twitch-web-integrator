package com.impurity.twitchwebintegrator.controller.handler.steam;

import com.impurity.twitchwebintegrator.exception.steam.SteamClientLibraryHttpRequestException;
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
public class SteamClientExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SteamClientLibraryHttpRequestException.class)
    protected ResponseEntity<Object> handledSteamClientLibraryRequestException(
            final SteamClientLibraryHttpRequestException ex,
            final WebRequest request
    ) {
        log.info("The Steam Client was unable to successfully complete the get library request: {}:{}", ex.getStatus(), ex.getMessage());
        return handleExceptionInternal(
                ex, "Get Steam Library failed", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request
        );
    }
}
