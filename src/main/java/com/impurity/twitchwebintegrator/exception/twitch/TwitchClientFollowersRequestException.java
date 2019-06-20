package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author tmk2003
 */
public class TwitchClientFollowersRequestException extends RuntimeException {
    private static final long serialVersionUID = 8013263773819717060L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param cause Causing exception
     */
    public TwitchClientFollowersRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
