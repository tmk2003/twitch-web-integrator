package com.impurity.twitchwebintegrator.exception.steam;

/**
 * @author tmk2003
 */
public class SteamLibraryCreationException extends SteamException {
    private final long serialVersionUID = -8859427755595616816L;

    /**
     * Generic Twitch Follower Exception
     *
     * @param message Explanatory message
     */
    public SteamLibraryCreationException(String message) {
        super(message);
    }

    /**
     * Generic Twitch Follower Exception
     *
     * @param message Explanatory message
     * @param cause Stack track cause
     */
    public SteamLibraryCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
