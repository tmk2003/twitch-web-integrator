package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author tmk2003
 */
public class TwitchClientStreamRequestException extends RuntimeException {
    private static final long serialVersionUID = -7639579083797350975L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param cause Causing exception
     */
    public TwitchClientStreamRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
