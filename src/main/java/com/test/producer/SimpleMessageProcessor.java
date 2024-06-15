package com.test.producer;

import com.test.interfaces.MessageProcessor;
import com.test.model.Message;

/**
 * This class represents a simple message processor that processes messages.
 * It implements the MessageProcessor interface.
 *
 * @author Rahil Sathwara
 * @since 2024-06-15
 */
public class SimpleMessageProcessor implements MessageProcessor {

    /**
     * Processes the given message.
     *
     * @param message the message to process
     * @return true if the message was processed successfully, false otherwise
     */
    @Override
    public boolean process(Message message) {
        // Simulate message processing
        return !message.getMessage().contains("error");
    }
}