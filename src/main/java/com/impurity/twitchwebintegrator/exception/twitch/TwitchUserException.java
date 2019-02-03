package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author Tyler Kokoszka
 */
public class TwitchUserException extends TwitchException {
    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     */
    public TwitchUserException(String message) {
        super(message);
    }

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param cause Stack track cause
     */
    public TwitchUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
