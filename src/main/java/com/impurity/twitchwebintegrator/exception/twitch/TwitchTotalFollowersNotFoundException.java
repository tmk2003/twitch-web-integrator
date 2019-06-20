package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author tmk2003
 */
public class TwitchTotalFollowersNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1796569008390000579L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     */
    public TwitchTotalFollowersNotFoundException(String message) {
        super(message);
    }
}
