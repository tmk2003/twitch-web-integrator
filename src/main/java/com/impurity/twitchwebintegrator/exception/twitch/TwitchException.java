package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author tmk2003
 */
public class TwitchException extends RuntimeException {
    private static final long serialVersionUID = -8399080409284314078L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     */
    public TwitchException(String message) {
        super(message);
    }

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
