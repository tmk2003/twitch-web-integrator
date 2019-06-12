package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author tmk2003
 */
public class TwitchFollowerNotFoundException extends TwitchException {
    private final long serialVersionUID = -3103448852655456172L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     */
    public TwitchFollowerNotFoundException(String message) {
        super(message);
    }
}
