package com.impurity.twitchwebintegrator.twitch.exception;

import com.impurity.twitchwebintegrator.exception.HttpRequestException;
import org.springframework.http.HttpStatus;

/**
 * @author tmk2003
 */
public class TwitchClientStreamHttpRequestException extends HttpRequestException {
    private static final long serialVersionUID = -7639579083797350975L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param status http status
     * @param cause Causing exception
     */
    public TwitchClientStreamHttpRequestException(String message, HttpStatus status, Throwable cause) {
        super(message, status, cause);
    }
}
