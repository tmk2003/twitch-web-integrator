package com.impurity.twitchwebintegrator.exception.steam;

/**
 * @author tmk2003
 */
public class SteamLibraryAmountNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -7406291465507098804L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     */
    public SteamLibraryAmountNotFoundException(String message) {
        super(message);
    }
}
