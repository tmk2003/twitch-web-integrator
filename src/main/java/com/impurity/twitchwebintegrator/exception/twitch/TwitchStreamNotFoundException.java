package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author tmk2003
 */
public class TwitchStreamNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 5080983408617973600L;

    /**
     * Generic Twitch stream not found Exception
     *
     * @param message Explanatory message
     */
    public TwitchStreamNotFoundException(String message) {
        super(message);
    }
}
