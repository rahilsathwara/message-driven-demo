package com.test.producer;

import com.test.model.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleMessageProcessorTest {

    @Test
    public void testProcessMessageWithoutError() {
        // Given
        SimpleMessageProcessor processor = new SimpleMessageProcessor();
        Message message = new Message("This is a test message");

        // When
        boolean result = processor.process(message);

        // Then
        assertTrue(result);
    }

    @Test
    public void testProcessMessageWithError() {
        // Given
        SimpleMessageProcessor processor = new SimpleMessageProcessor();
        Message message = new Message("This is an error message");

        // When
        boolean result = processor.process(message);

        // Then
        assertFalse(result);
    }
}
