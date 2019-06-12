package com.impurity.twitchwebintegrator.exception.steam;

/**
 * @author tmk2003
 */
public class SteamException extends RuntimeException {
    private final long serialVersionUID = 950798548622562062L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     */
    public SteamException(String message) {
        super(message);
    }

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param cause Stack track cause
     */
    public SteamException(String message, Throwable cause) {
        super(message, cause);
    }
}
