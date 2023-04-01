package com.example.eat4u.exceptions;

/**
 * An exception to be thrown by methods that are not implemented yet
 */
public class UnimplementedException extends RuntimeException {

    public UnimplementedException() {
        super("Method not implemented!");
    }
}
