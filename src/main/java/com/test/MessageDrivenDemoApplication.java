package com.test;

import com.test.consumer.Consumer;
import com.test.interfaces.MessageProcessor;
import com.test.logger.Logger;
import com.test.model.MessageFactory;
import com.test.model.SimpleMessageFactory;
import com.test.producer.Producer;
import com.test.producer.SimpleMessageProcessor;
import com.test.queue.MessageQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageDrivenDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageDrivenDemoApplication.class, args);

		MessageQueue messageQueue = new MessageQueue();
		MessageFactory messageFactory = new SimpleMessageFactory();
		MessageProcessor messageProcessor = new SimpleMessageProcessor();
		Logger logger = new Logger();

		int messageRange = 10; // Set the dynamic range for the number of messages to produce
		Producer producer = new Producer(messageQueue, messageFactory, messageRange);
		Consumer consumer = new Consumer(messageQueue, messageProcessor, logger, messageRange);

		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);

		producerThread.start();
		consumerThread.start();

		try {
			producerThread.join();
			consumerThread.interrupt();
			consumerThread.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		logger.log();
	}
}
