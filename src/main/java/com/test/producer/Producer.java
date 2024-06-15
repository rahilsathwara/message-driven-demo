package com.test.producer;

import com.test.model.Message;
import com.test.model.MessageFactory;
import com.test.queue.MessageQueue;

import java.util.stream.IntStream;

/**
 * This class represents a producer that creates messages and adds them to a message queue.
 * It implements the Runnable interface.
 *
 * @author Rahil Sathwara
 * @since 2024-06-15
 */
public class Producer implements Runnable {
    private final MessageQueue messageQueue;
    private final MessageFactory messageFactory;
    private final int range;

    public Producer(MessageQueue messageQueue, MessageFactory messageFactory, int range) {
        this.messageQueue = messageQueue;
        this.messageFactory = messageFactory;
        this.range = range;
    }

    /**
     * Runs the producer, creating messages and adding them to the message queue.
     */
    @Override
    public void run() {
        IntStream.range(0, range).forEach(i -> {
            String content = "Message-" + i;
            Message message = messageFactory.createMessage(content);
            try {
                messageQueue.produce(message);
                System.out.println("Produced: " + content);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}