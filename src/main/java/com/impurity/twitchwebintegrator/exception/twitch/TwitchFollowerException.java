package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author tmk2003
 */
public class TwitchFollowerException extends TwitchException {
    private final long serialVersionUID = -2209610310903549177L;

    /**
     * Generic Twitch Follower Exception
     *
     * @param message Explanatory message
     */
    public TwitchFollowerException(String message) {
        super(message);
    }

    /**
     * Generic Twitch Follower Exception
     *
     * @param message Explanatory message
     * @param cause Stack track cause
     */
    public TwitchFollowerException(String message, Throwable cause) {
        super(message, cause);
    }
}
