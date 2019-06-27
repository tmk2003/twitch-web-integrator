package com.impurity.twitchwebintegrator.osrs.exception;

import com.impurity.twitchwebintegrator.exception.HttpRequestException;
import org.springframework.http.HttpStatus;

/**
 * @author tmk2003
 */
public class OsrsClientPlayerHttpRequestException extends HttpRequestException {
    private static final long serialVersionUID = -4955276495660819867L;

    /**
     * Generic Osrs Exception
     *
     * @param message Explanatory message
     * @param status Http status
     * @param cause The causing exception
     */
    public OsrsClientPlayerHttpRequestException(String message, HttpStatus status, Throwable cause) {
        super(message, status, cause);
    }
}
