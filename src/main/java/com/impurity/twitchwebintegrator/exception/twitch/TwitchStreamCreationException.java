package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author tmk2003
 */
public class TwitchStreamCreationException extends TwitchException {
    private final long serialVersionUID = 8735249218148979603L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param cause Stack track cause
     */
    public TwitchStreamCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
