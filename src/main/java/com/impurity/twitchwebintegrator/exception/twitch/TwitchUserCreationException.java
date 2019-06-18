package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author tmk2003
 */
public class TwitchUserCreationException extends TwitchException {
    private static final long serialVersionUID = -1672930411967682149L;

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
