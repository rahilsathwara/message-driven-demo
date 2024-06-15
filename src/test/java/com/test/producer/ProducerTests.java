package com.test.producer;

import com.test.model.Message;
import com.test.model.MessageFactory;
import com.test.queue.MessageQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ProducerTests {

    private MessageQueue messageQueue;
    private MessageFactory messageFactory;
    private Producer producer;

    @BeforeEach
    public void setUp() {
        messageQueue = mock(MessageQueue.class);
        messageFactory = mock(MessageFactory.class);
        producer = new Producer(messageQueue, messageFactory, 3); // Produce three messages for test
    }

    @Test
    public void testRunSuccess() throws InterruptedException {
        when(messageFactory.createMessage(anyString())).thenReturn(new Message("Message-0"), new Message("Message-1"), new Message("Message-2"));

        producer.run();

        verify(messageFactory, times(3)).createMessage(anyString());
        verify(messageQueue, times(3)).produce(any(Message.class));
    }

    @Test
    public void testRunWithInterruptedException() throws InterruptedException {
        when(messageFactory.createMessage(anyString())).thenReturn(new Message("Message-0"));

        // Throw InterruptedException when messageQueue.produce is called
        doThrow(new InterruptedException()).when(messageQueue).produce(any(Message.class));

        // Mock only the first call to messageQueue.produce
        doNothing().doThrow(new InterruptedException()).when(messageQueue).produce(any(Message.class));

        producer.run();

        // Verify that Thread.currentThread().interrupt() is called
        verify(messageQueue, times(3)).produce(any(Message.class)); // 3 times because the first call succeeds, but the next two fail
        verify(messageFactory, times(3)).createMessage(anyString()); // Verify that createMessage is called for each message
        verify(messageQueue, never()).consume(); // Ensure that consume is not called
    }
}
