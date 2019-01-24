package com.impurity.twitchwebintegrator.exception;

/**
 * @author Tyler Kokoszka
 */
public class TwitchException extends RuntimeException {
    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param cause Stack track cause
     */
    public TwitchException(String message, Throwable cause) {
        super(message, cause);
    }
}