package com.test.model;

/**
 * Simple implementation of MessageFactory that creates Message objects.
 * Messages created by this factory have no special formatting or customization.
 *
 * @author Rahil Sathwara
 * @since 2024-06-15
 */
public class SimpleMessageFactory extends MessageFactory {
    @Override
    public Message createMessage(String content) {
        return new Message(content);
    }
}
