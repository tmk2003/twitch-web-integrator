package com.impurity.twitchwebintegrator.exception;

public class TwitchStreamException extends TwitchException {
    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param cause Stack track cause
     */
    public TwitchStreamException(String message, Throwable cause) {
        super(message, cause);
    }
}
