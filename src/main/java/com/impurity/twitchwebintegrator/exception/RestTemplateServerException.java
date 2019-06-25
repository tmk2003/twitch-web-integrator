package com.impurity.twitchwebintegrator.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author tmk2003
 */
public class RestTemplateServerException extends RuntimeException {
    private static final long serialVersionUID = 2832075658057627897L;

    @Getter
    private final HttpStatus status;

    /**
     * Generic Rest Template Exception
     * @param message Explanatory Message
     * @param status Http status returned
     * @param cause Exception causing this
     */
    public RestTemplateServerException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.status = status;
    }
}
