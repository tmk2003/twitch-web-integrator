package com.impurity.twitchwebintegrator.exception;

/**
 * @author tmk2003
 */
public class RestTemplateClientException extends RuntimeException {
    private static final long serialVersionUID = 3940856042422350250L;

    /**
     * Generic Rest Template Exception
     *
     * @param message Explanatory message
     */
    public RestTemplateClientException(String message) {
        super(message);
    }
}
