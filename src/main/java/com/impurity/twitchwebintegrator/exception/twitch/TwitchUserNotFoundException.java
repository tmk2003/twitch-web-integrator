package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author Tyler Kokoszka
 */
public class TwitchUserNotFoundException extends TwitchUserException {
    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     */
    public TwitchUserNotFoundException(String message) {
        super(message);
    }

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param cause Stack track cause
     */
    public TwitchUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
