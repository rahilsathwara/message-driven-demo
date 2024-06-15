package com.test.model;

/**
 * Abstract factory for creating messages.
 * Subclasses should implement the createMessage method to provide specific message creation logic.
 *
 * @author Rahil Sathwara
 * @since 2024-06-15
 */
public abstract class MessageFactory {
    public abstract Message createMessage(String content);
}