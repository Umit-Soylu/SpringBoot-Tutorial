package com.bilgeadam.tutorial.springboot.utils;

public class RecordNotFound extends RuntimeException{
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public RecordNotFound(String message, Object object) {
        super("Requested " + object + " cannot be found.");

    }
}
