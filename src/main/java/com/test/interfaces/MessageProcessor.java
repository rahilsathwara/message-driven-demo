package com.test.interfaces;

import com.test.model.Message;

/**
 * This interface represents a message processor.
 * It provides a method to process a message.
 *
 * @author Rahil Sathwara
 * @since 2024-06-15
 */
public interface MessageProcessor {

    /**
     * Processes the given message.
     *
     * @param message the message to process
     * @return true if the processing was successful, false otherwise
     */
    boolean process(Message message);
}