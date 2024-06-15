package com.test.queue;

import com.test.model.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * This class represents a message queue that stores messages for processing.
 * Messages can be produced to the queue and consumed from the queue.
 *
 * @author Rahil Sathwara
 * @since 2024-06-15
 */
public class MessageQueue {

    private final BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    public void produce(Message message) throws InterruptedException {
        queue.put(message);
    }

    public Message consume() throws InterruptedException {
        return queue.take();
    }
}
