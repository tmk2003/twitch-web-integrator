package com.impurity.twitchwebintegrator.exception.osrs;

import com.impurity.twitchwebintegrator.exception.HttpRequestException;
import org.springframework.http.HttpStatus;

/**
 * @author tmk2003
 */
public class OsrsItemHttpRequestException extends HttpRequestException {
    private static final long serialVersionUID = 5869199903449373726L;

    /**
     * Generic Osrs Exception
     *
     * @param message Explanatory message
     * @param status Http status
     * @param cause The causing exception
     */
    public OsrsItemHttpRequestException(String message, HttpStatus status, Throwable cause) {
        super(message, status, cause);
    }
}
