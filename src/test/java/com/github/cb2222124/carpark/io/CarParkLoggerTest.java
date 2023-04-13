package com.github.cb2222124.carpark.io;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.text.MessageFormat;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Car park logger tests.
 */
public class CarParkLoggerTest {

    /**
     * Given a string representation of an event is logged, then the resulting output should be the event prefixed
     * with the current timestamp.
     */
    @Test
    public void loggerLogsEvent() {
        assertDoesNotThrow(() -> {
            CarParkLogger carParkLogger = new CarParkLogger();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            carParkLogger.log("Event", out);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String expectedLog = MessageFormat.format("{0} {1}\n", timestamp, "Event");

            assertEquals(expectedLog, out.toString());
        });

    }
}
