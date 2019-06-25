package com.impurity.twitchwebintegrator.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author tmk2003
 */
public abstract class HttpRequestException extends RuntimeException {
    private static final long serialVersionUID = 924869611273733372L;

    @Getter
    private final HttpStatus status;

    /**
     * Generic Rest Template Exception
     * @param message Explanatory Message
     * @param status Http status returned
     * @param cause Exception causing this
     */
    public HttpRequestException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.status = status;
    }
}
