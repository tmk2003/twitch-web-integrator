package com.impurity.twitchwebintegrator.osrs.exception;

/**
 * @author tmk2003
 */
public class OsrsPlayerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -5675972332879532275L;

    /**
     * Generic Osrs Exception
     *
     * @param message Explanatory message
     */
    public OsrsPlayerNotFoundException(String message) {
        super(message);
    }
}
