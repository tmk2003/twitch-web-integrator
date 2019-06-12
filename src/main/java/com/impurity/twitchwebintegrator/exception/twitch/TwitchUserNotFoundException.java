package com.impurity.twitchwebintegrator.exception.twitch;

/**
 * @author tmk2003
 */
public class TwitchUserNotFoundException extends TwitchUserException {
    private final long serialVersionUID = 7238606532836454726L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     */
    public TwitchUserNotFoundException(String message) {
        super(message);
    }
}
