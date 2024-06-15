package com.test.consumer;

import com.test.interfaces.ApplicationLogger;
import com.test.interfaces.MessageProcessor;
import com.test.model.Message;
import com.test.queue.MessageQueue;

import java.util.stream.IntStream;

/**
 * This class represents a consumer that processes messages from a message queue.
 * It retrieves messages from the queue, processes them using a message processor,
 * and updates a logger based on the processing result.
 *
 * @author Rahil Sathwara
 * @since 2024-06-15
 */
public class Consumer implements Runnable {
    private final MessageQueue messageQueue;
    private final MessageProcessor messageProcessor;
    private final ApplicationLogger logger;
    private final int numberOfMessagesToProcess;

    public Consumer(MessageQueue messageQueue, MessageProcessor messageProcessor, ApplicationLogger logger, int numberOfMessagesToProcess) {
        this.messageQueue = messageQueue;
        this.messageProcessor = messageProcessor;
        this.logger = logger;
        this.numberOfMessagesToProcess = numberOfMessagesToProcess;
    }

    /**
     * Runs the consumer, processing messages from the message queue.
     * It retrieves messages from the queue, processes them using the message processor,
     * and updates the logger based on the processing result.
     */
    @Override
    public void run() {
        IntStream.range(0, numberOfMessagesToProcess)
                .forEach(i -> {
                    try {
                        Message message = messageQueue.consume();
                        boolean success = messageProcessor.process(message);
                        logger.update(success);
                        System.out.println("Consumed: " + message.getMessage());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
    }
}