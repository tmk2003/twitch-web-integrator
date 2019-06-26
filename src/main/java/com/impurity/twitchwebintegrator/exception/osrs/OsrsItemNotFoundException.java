package com.impurity.twitchwebintegrator.exception.osrs;

/**
 * @author tmk2003
 */
public class OsrsItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3513739686319721275L;

    /**
     * Generic Osrs Exception
     *
     * @param message Explanatory message
     */
    public OsrsItemNotFoundException(String message) {
        super(message);
    }
}
