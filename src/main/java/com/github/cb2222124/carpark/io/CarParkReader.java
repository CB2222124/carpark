package com.github.cb2222124.carpark.io;

import com.github.cb2222124.carpark.CarPark;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Utility class for deserializing car park state from an input stream.
 */
public class CarParkReader {

    /**
     * Reads car park state from an input stream.
     *
     * @param in Input stream.
     * @return Deserialized CarPark instance.
     * @throws IOException            if an I/O error occurs.
     * @throws ClassNotFoundException if the stored object cannot be deserialized.
     */
    public CarPark read(InputStream in) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(in)) {
            return (CarPark) ois.readObject();
        }
    }
}