package com.github.cb2222124.carpark.io;

import java.io.*;
import java.sql.Timestamp;
import java.text.MessageFormat;

/**
 * Utility class for logging car park state changes to an output stream.
 */
public class CarParkLogger {

    /**
     * Logs the given event to an output stream, prefixed with the current timestamp.
     *
     * @param event The event to be logged.
     * @param out   The output stream.
     * @throws IOException If an I/O error occurs.
     */
    public void log(String event, OutputStream out) throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String log = MessageFormat.format("{0} {1}\n", timestamp, event);

        try (OutputStreamWriter osw = new OutputStreamWriter(out);
             BufferedWriter bw = new BufferedWriter(osw)) {
            bw.append(log);
        }
    }
}
