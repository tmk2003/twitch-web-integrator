package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author tmk2003
 */
public class TwitchClientUserRequestException extends RuntimeException {
    private static final long serialVersionUID = -2434054520504668053L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param cause Causing exception
     */
    public TwitchClientUserRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
