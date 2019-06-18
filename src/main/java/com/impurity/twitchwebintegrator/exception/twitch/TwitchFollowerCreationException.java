package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author tmk2003
 */
public class TwitchFollowerCreationException extends TwitchException {
    private static final long serialVersionUID = -2209610310903549177L;

    /**
     * Generic Twitch Follower Exception
     *
     * @param message Explanatory message
     * @param cause Stack track cause
     */
    public TwitchFollowerCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
