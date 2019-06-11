package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author tmk2003
 */
public class TwitchStreamNotFoundException extends TwitchException {
    /**
     * Generic Twitch stream not found Exception
     *
     * @param message Explanatory message
     * @param cause Stack track cause
     */
    public TwitchStreamNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
