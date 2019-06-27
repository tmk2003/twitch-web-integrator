package com.impurity.twitchwebintegrator.steam.exception;

/**
 * @author tmk2003
 */
public class SteamLibraryNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2813516408240107708L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     */
    public SteamLibraryNotFoundException(String message) {
        super(message);
    }
}
