package com.github.cb2222124.carpark.io;

import com.github.cb2222124.carpark.CarPark;
import com.github.cb2222124.carpark.CarParkEventListener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * Wrapper implementation of CarParkEventListener for car park I/O functionality that depends on state change.
 * I/O exceptions are handled within this class instead of propagating up the call stack,
 * as the failure of logging and data backups should not result in application termination.
 */
public class CarParkFileHandler implements CarParkEventListener {

    //In a real-world application paths would be more appropriately stored in a configuration file.
    private static final String LOG_PATH = "carpark.log";
    private static final String DATA_PATH = "carpark.dat";

    private final CarParkLogger logger = new CarParkLogger();
    private final CarParkWriter writer = new CarParkWriter();

    @Override
    public void vehicleEntered(CarPark carPark, String vehicle) {
        writeLog("Vehicle entered: " + vehicle);
        writeData(carPark);
    }

    @Override
    public void vehicleExited(CarPark carPark, String vehicle) {
        writeLog("Vehicle exited: " + vehicle);
        writeData(carPark);
    }

    @Override
    public void listenerRegistered(CarPark carPark) {
        writeData(carPark);
    }

    /**
     * Serialises and streams a car park to a file.
     *
     * @param carPark Car park.
     */
    private void writeData(CarPark carPark) {
        try (FileOutputStream out = new FileOutputStream(DATA_PATH)) {
            writer.write(carPark, out);
        } catch (IOException e) {
            System.err.println(MessageFormat.format("Failed to serialize car park state to {0}: {1}",
                    DATA_PATH, e));
        }
    }

    /**
     * Appends an event to a log file.
     *
     * @param event String representation of the event to log.
     */
    private void writeLog(String event) {
        try (FileOutputStream out = new FileOutputStream(LOG_PATH, true)) {
            logger.log(event, out);
        } catch (IOException e) {
            System.err.println(MessageFormat.format("Failed to log car park event ({1}) to {2]: {3}",
                    LOG_PATH, "carpark.log", e));
        }
    }
}