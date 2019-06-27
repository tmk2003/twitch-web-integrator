package com.impurity.twitchwebintegrator.twitch.exception;

/**
 * @author tmk2003
 */
public class TwitchStreamNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 8580019017578654606L;

    /**
     * Generic Twitch stream not found Exception
     *
     * @param message Explanatory message
     */
    public TwitchStreamNotFoundException(String message) {
        super(message);
    }
}
