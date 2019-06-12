package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author tmk2003
 */
public class TwitchUserCreationException extends TwitchException {
    private final long serialVersionUID = -1672930411967682149L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     */
    public TwitchUserCreationException(String message) {
        super(message);
    }

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param cause Stack track cause
     */
    public TwitchUserCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
