package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author tmk2003
 */
public class TwitchUserException extends TwitchException {
    private final long serialVersionUID = -1672930411967682149L;

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
