package com.bilgeadam.tutorial.springboot.utils;

public class IllegalRestArgument extends IllegalArgumentException {
    /**
     * Constructs an {@code IllegalArgumentException} with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public IllegalRestArgument(String s) {
        super(s);
    }
}
