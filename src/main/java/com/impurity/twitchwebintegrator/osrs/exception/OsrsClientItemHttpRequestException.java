package com.impurity.twitchwebintegrator.osrs.exception;

import com.impurity.twitchwebintegrator.exception.HttpRequestException;
import org.springframework.http.HttpStatus;

/**
 * @author tmk2003
 */
public class OsrsClientItemHttpRequestException extends HttpRequestException {
    private static final long serialVersionUID = 5869199903449373726L;

    /**
     * Generic Osrs Exception
     *
     * @param message Explanatory message
     * @param status Http status
     * @param cause The causing exception
     */
    public OsrsClientItemHttpRequestException(String message, HttpStatus status, Throwable cause) {
        super(message, status, cause);
    }
}
