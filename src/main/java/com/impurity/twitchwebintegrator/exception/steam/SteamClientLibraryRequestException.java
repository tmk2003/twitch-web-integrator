package com.impurity.twitchwebintegrator.exception.steam;

/**
 * @author tmk2003
 */
public class SteamClientLibraryRequestException extends RuntimeException {
    private static final long serialVersionUID = 6688799437661475781L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param cause The causing exception
     */
    public SteamClientLibraryRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
