package com.impurity.twitchwebintegrator.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author tmk2003
 */
public class RestTemplateClientException extends Exception {
    private static final long serialVersionUID = 3940856042422350250L;

    @Getter
    private final HttpStatus status;

    /**
     * Generic Rest Template Exception
     * @param message Explanatory Message
     * @param status Http status returned
     * @param cause Exception causing this
     */
    public RestTemplateClientException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.status = status;
    }
}
