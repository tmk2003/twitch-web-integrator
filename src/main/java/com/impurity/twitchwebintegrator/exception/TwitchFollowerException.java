package com.impurity.twitchwebintegrator.exception;

/**
 * @author Tyler Kokoszka
 */
public class TwitchFollowerException extends TwitchException {
    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param cause Stack track cause
     */
    public TwitchFollowerException(String message, Throwable cause) {
        super(message, cause);
    }
}
