package com.impurity.twitchwebintegrator.exception.steam;

/**
 * @author tmk2003
 */
public class SteamLibraryNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -805952780820062553L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     */
    public SteamLibraryNotFoundException(String message) {
        super(message);
    }
}
