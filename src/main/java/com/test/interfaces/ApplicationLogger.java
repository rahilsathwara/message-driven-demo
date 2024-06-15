package com.test.interfaces;

/**
 * This interface represents a logger for an application.
 * It provides a method to update the logger with the success or failure of an operation.
 *
 * @author Rahil Sathwara
 * @since 2024-06-15
 */
public interface ApplicationLogger {

    /**
     * Updates the logger with the success or failure of an operation.
     *
     * @param success true if the operation was successful, false otherwise
     */
    void update(boolean success);
}