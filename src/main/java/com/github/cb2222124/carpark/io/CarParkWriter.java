package com.github.cb2222124.carpark.io;

import com.github.cb2222124.carpark.CarPark;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Utility class for writing the serialized state of a car park to an output stream.
 */
public class CarParkWriter {


    /**
     * Writes the serialized state of the given car park to an output stream.
     *
     * @param carPark The car park to be stored.
     * @param out     The output stream.
     * @throws IOException If an I/O error occurs.
     */
    public void write(CarPark carPark, OutputStream out) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeObject(carPark);
        }
    }
}
