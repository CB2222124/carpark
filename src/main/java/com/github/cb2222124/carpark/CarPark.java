package com.github.cb2222124.carpark;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Car park data structure.
 */
public class CarPark implements Serializable {

    /**
     * Maximum vehicles the car park can hold.
     */
    private final int capacity;

    /**
     * HashSet of vehicle registrations in the car park.
     */
    private final HashSet<String> vehicles;

    //Transient keyword used to specify observers of this class should not be part of its persistent state.
    private transient ArrayList<CarParkEventListener> changeListeners = new ArrayList<>();

    /**
     * Instantiates a new car park with given capacity.
     *
     * @param capacity Car park capacity.
     */
    public CarPark(int capacity) {
        this.capacity = capacity;
        this.vehicles = new HashSet<>();
    }

    /**
     * Instantiates a new car park with given capacity and vehicle registration set.
     *
     * @param capacity Car park capacity.
     * @param vehicles Vehicle set to be used.
     * @throws CarParkFullException If provided vehicle set exceeds the provided capacity.
     */
    public CarPark(int capacity, HashSet<String> vehicles) throws CarParkFullException {
        if (vehicles.size() > capacity) throw new CarParkFullException("Vehicle set exceeds car park capacity");
        this.capacity = capacity;
        this.vehicles = vehicles;
    }

    /**
     * Adds a vehicle to the car park. Fires a vehicle entered event for listeners if the operation is successful.
     *
     * @param vehicle Vehicle registration.
     * @throws CarParkStateException If the given vehicle is already present in the car park,
     *                               or if the capacity of the car park will be exceeded with this operation.
     */
    public void addVehicle(String vehicle) throws CarParkStateException {
        if (vehicles.size() >= capacity) throw new CarParkFullException("Car park capacity reached");
        if (!vehicles.add(vehicle)) throw new CarParkStateException("Vehicle registration already present");
        for (CarParkEventListener listener : changeListeners) listener.vehicleEntered(this, vehicle);
    }

    /**
     * Removes a vehicle from the car park. Fires a vehicle exited event for listeners if the operation is successful.
     *
     * @param vehicle Vehicle registration.
     * @throws CarParkStateException If the given vehicle is not present within the car park.
     */
    public void removeVehicle(String vehicle) throws CarParkStateException {
        if (!vehicles.remove(vehicle)) throw new CarParkStateException("Vehicle registration not present");
        for (CarParkEventListener listener : changeListeners) listener.vehicleExited(this, vehicle);
    }

    /**
     * Checks if a given vehicle is present within the car park.
     *
     * @param vehicle Vehicle registration.
     * @return True if the vehicle is present, false otherwise.
     */
    @SuppressWarnings("unused")
    public boolean containsVehicle(String vehicle) {
        return vehicles.contains(vehicle);
    }

    /**
     * Gets an unmodifiable set of vehicles.
     *
     * @return Unmodifiable vehicle set.
     */
    public Set<String> getVehicles() {
        return Collections.unmodifiableSet(vehicles);
    }

    /**
     * Gets the current number of vehicles within the car park.
     *
     * @return The number of vehicles.
     */
    @SuppressWarnings("unused")
    public int getVehicleCount() {
        return vehicles.size();
    }

    /**
     * Gets the capacity of the car park.
     *
     * @return The capacity.
     */
    @SuppressWarnings("unused")
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets the number of free spaces within the car park.
     *
     * @return The number of free spaces.
     */
    public int getFreeSpaces() {
        return capacity - vehicles.size();
    }

    /**
     * Adds a listener to the car park to be notified of specific state changes. New listeners are notified
     * of the current car park state.
     *
     * @param listener Listener.
     */
    public void addChangeListener(CarParkEventListener listener) {
        changeListeners.add(listener);
        listener.listenerRegistered(this);
    }

    /**
     * Returns a String representation of the car park where the capacity is specified followed
     * by a list of the vehicles present in the vehicle set.
     *
     * @return String representation of the car park.
     */
    @Override
    public String toString() {
        return "Capacity " + capacity + ", Vehicles [" + String.join(", ", vehicles) + ']';
    }

    /**
     * Implementation of the Serialization interfaces readObject method to facilitate the initialisation of
     * transient field changeListeners after reading from the stream and restoring state.
     *
     * @param in ObjectInputStream
     * @throws IOException            If an IO error occurs.
     * @throws ClassNotFoundException If the class of a serialised object cannot be found.
     */
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        changeListeners = new ArrayList<>();
    }
}