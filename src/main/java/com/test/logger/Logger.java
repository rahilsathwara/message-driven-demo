package com.test.logger;

import com.test.interfaces.ApplicationLogger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class represents a logger that counts successful and failed operations.
 * It implements the ApplicationLogger interface.
 *
 * @author Rahil Sathwara
 * @since 2024-06-15
 */
public class Logger implements ApplicationLogger {
    private final AtomicInteger successCount = new AtomicInteger(0);
    private final AtomicInteger errorCount = new AtomicInteger(0);

    public int getSuccessCount() {
        return successCount.get();
    }

    public int getErrorCount() {
        return errorCount.get();
    }

    /**
     * Updates the counts based on the success of an operation.
     *
     * @param success true if the operation was successful, false otherwise
     */
    @Override
    public void update(boolean success) {
        if (success) {
            successCount.incrementAndGet();
        } else {
            errorCount.incrementAndGet();
        }
    }

    /**
     * Logs the total number of successful and failed operations.
     */
    public void log() {
        System.out.println("Total messages processed successfully: " + successCount.get());
        System.out.println("Total errors encountered: " + errorCount.get());
    }
}