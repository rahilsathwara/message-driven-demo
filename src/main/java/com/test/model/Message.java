package com.test.model;

/**
 * Represents a message with a string content.
 * Messages are created with a string content and provide a method to retrieve the content.
 *
 * @author Rahil Sathwara
 * @since 2024-06-15
 */
public class Message {

    private final String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}