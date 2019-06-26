package com.impurity.twitchwebintegrator.exception.steam;

import com.impurity.twitchwebintegrator.exception.HttpRequestException;
import org.springframework.http.HttpStatus;

/**
 * @author tmk2003
 */
public class SteamClientLibraryHttpRequestException extends HttpRequestException {
    private static final long serialVersionUID = 6688799437661475781L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param status Http status
     * @param cause The causing exception
     */
    public SteamClientLibraryHttpRequestException(String message, HttpStatus status, Throwable cause) {
        super(message, status, cause);
    }
}
