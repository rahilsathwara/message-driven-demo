package com.test.consumer;

import com.test.interfaces.ApplicationLogger;
import com.test.interfaces.MessageProcessor;
import com.test.model.Message;
import com.test.queue.MessageQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ConsumerTests {
    private MessageQueue messageQueue;
    private MessageProcessor messageProcessor;
    private ApplicationLogger logger;
    private Consumer consumer;

    @BeforeEach
    public void setUp() {
        messageQueue = mock(MessageQueue.class);
        messageProcessor = mock(MessageProcessor.class);
        logger = mock(ApplicationLogger.class);
        consumer = new Consumer(messageQueue, messageProcessor, logger, 3); // Process three messages for test
    }

    @Test
    public void testRunWithSuccessfulMessageProcessing() throws InterruptedException {
        // Given
        when(messageQueue.consume()).thenReturn(new Message("Message-0"), new Message("Message-1"), new Message("Message-2"));
        when(messageProcessor.process(any(Message.class))).thenReturn(true);

        // When
        consumer.run();

        // Then
        verify(messageQueue, times(3)).consume(); // Verify consume is called 3 times
        verify(messageProcessor, times(3)).process(any(Message.class)); // Verify process is called 3 times
        verify(logger, times(3)).update(true); // Verify logger update with success for each message
    }

    @Test
    public void testRunWithFailedMessageProcessing() throws InterruptedException {
        // Given
        when(messageQueue.consume()).thenReturn(new Message("Message-0"), new Message("Message-1"), new Message("Message-2"));
        when(messageProcessor.process(any(Message.class))).thenReturn(false);

        // When
        consumer.run();

        // Then
        verify(messageQueue, times(3)).consume(); // Verify consume is called 3 times
        verify(messageProcessor, times(3)).process(any(Message.class)); // Verify process is called 3 times
        verify(logger, times(3)).update(false); // Verify logger update with failure for each message
    }

    @Test
    public void testRunWithInterruptedException() throws InterruptedException {
        // Given
        when(messageQueue.consume()).thenReturn(new Message("Message-0"))
                .thenThrow(new InterruptedException())
                .thenThrow(new InterruptedException());

        // When
        consumer.run();

        // Then
        verify(messageQueue, times(3)).consume(); // Verify consume is called 3 times
        verify(messageProcessor, times(1)).process(any(Message.class)); // Verify process is called only once
        verify(logger, times(1)).update(anyBoolean()); // Verify logger update is called only once
    }
}
