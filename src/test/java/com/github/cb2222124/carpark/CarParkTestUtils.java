package com.github.cb2222124.carpark;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;

/**
 * Utility class for writing car park tests.
 */
public class CarParkTestUtils {


    /**
     * Wrapper for easily initialising a new car park by taking vehicles as varargs.
     *
     * @param capacity Capacity.
     * @param vehicles Vehicle registrations.
     * @return Car park.
     * @throws CarParkFullException If the number of vehicles exceeds the capacity.
     */
    public CarPark createCarPark(int capacity, String... vehicles) throws CarParkFullException {
        return new CarPark(capacity, vehicleHashSetFromArgs(vehicles));
    }

    /**
     * Serializes a car park and streams it to a byte array instead of a file.
     *
     * @param carPark Car park.
     * @return Serialized car park.
     * @throws IOException If an I/O error occurs.
     */
    public byte[] serializeCarPark(CarPark carPark) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(carPark);
        return bos.toByteArray();
    }

    /**
     * Deserializes a byte array to a car park.
     *
     * @param carPark Byte array representation of a car park.
     * @return Car park.
     * @throws IOException            If an I/O error occurs.
     * @throws ClassNotFoundException If the stored object cannot be deserialized.
     */
    public CarPark deserializeCarPark(byte[] carPark) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(carPark);
        ObjectInputStream in = new ObjectInputStream(bis);
        return (CarPark) in.readObject();
    }

    /**
     * Replaces the standard input stream with the provided input to facilitate testing of classes that
     * read from it (i.e. Scanner).
     *
     * @param input Simulated user input.
     */
    public void stubSystemScannerInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    /**
     * Reassigns the standard output stream to a new PrintStream and returns a reference to
     * a ByteArrayOutputStream.
     *
     * @return Output stream.
     */
    public ByteArrayOutputStream redirectStandardOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        return out;
    }

    /**
     * Reassigns the error output stream to a new PrintStream and returns a reference to
     * a ByteArrayOutputStream.
     *
     * @return Output stream.
     */
    public ByteArrayOutputStream redirectErrorOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setErr(new PrintStream(out));
        return out;
    }

    /**
     * Generates a vehicle HashSet from provided varargs.
     *
     * @param vehicles Vehicle registrations.
     * @return HashSet of vehicles.
     */
    public HashSet<String> vehicleHashSetFromArgs(String... vehicles) {
        return hashSetFromArgs(vehicles);
    }

    /**
     * Generates a generic HashSet from provided varargs.
     *
     * @param args Arguments.
     * @param <T>  Generic.
     * @return HashSet.
     */
    @SafeVarargs
    public final <T> HashSet<T> hashSetFromArgs(T... args) {
        HashSet<T> hashSet = new HashSet<>();
        Collections.addAll(hashSet, args);
        return hashSet;
    }
}
