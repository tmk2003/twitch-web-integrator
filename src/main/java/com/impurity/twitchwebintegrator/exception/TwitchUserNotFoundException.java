package com.impurity.twitchwebintegrator.exception;

/**
 * @author Tyler Kokoszka
 */
public class TwitchUserNotFoundException extends TwitchUserException {
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
