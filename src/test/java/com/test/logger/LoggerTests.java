package com.test.logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoggerTests {
        private Logger logger;
        private ByteArrayOutputStream outputStream;

        @BeforeEach
        public void setUp() {
            logger = new Logger();
            outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
        }

        @Test
        public void testUpdateSuccess() {
            logger.update(true);
            assertEquals(1, logger.getSuccessCount());
            assertEquals(0, logger.getErrorCount());
        }

        @Test
        public void testUpdateError() {
            logger.update(false);
            assertEquals(0, logger.getSuccessCount());
            assertEquals(1, logger.getErrorCount());
        }

        @Test
        public void testLog() {
            logger.update(true);
            logger.update(false);
            logger.log();

            String expectedOutput = "Total messages processed successfully: 1\n" +
                    "Total errors encountered: 1\n";
            assertEquals(expectedOutput, outputStream.toString());
        }

        @Test
        public void testLogNoCounts() {
            logger.log();

            String expectedOutput = "Total messages processed successfully: 0\n" +
                    "Total errors encountered: 0\n";
            assertEquals(expectedOutput, outputStream.toString());
        }

}
