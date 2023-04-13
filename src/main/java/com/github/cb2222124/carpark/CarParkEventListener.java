package com.github.cb2222124.carpark;

import java.util.EventListener;

/**
 * CarPark listener interface for receiving car park state changes.
 */
public interface CarParkEventListener extends EventListener {

    /**
     * To be invoked when a new vehicle is added to the car park.
     *
     * @param carPark The car park.
     * @param vehicle The vehicle.
     */
    void vehicleEntered(CarPark carPark, String vehicle);

    /**
     * To be invoked when a vehicle exits the car park.
     *
     * @param carPark The car park.
     * @param vehicle The vehicle.
     */
    void vehicleExited(CarPark carPark, String vehicle);

    /**
     * To be invoked on new listeners.
     * @param carPark The car park.
     */
    void listenerRegistered(CarPark carPark);
}
