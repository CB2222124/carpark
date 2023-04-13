package com.github.cb2222124.carpark;


/**
 * Sign that observes car park state changes and toggles a light used to indicate that
 * the car park is full.
 */
public class CarParkFullSign implements CarParkEventListener {

    //Implementation details of switching the light on and off have
    //been abstracted to a boolean field for this demonstration.
    private boolean lightOn;

    /**
     * Switches on the light when a new vehicle enters the car park if the car park capacity is reached.
     *
     * @param carPark The car park.
     * @param vehicle The vehicle.
     */
    @Override
    public void vehicleEntered(CarPark carPark, String vehicle) {
        lightOn = carPark.getFreeSpaces() == 0;
    }

    /**
     * Switches off the light when a vehicle exits the car park.
     *
     * @param carPark The car park.
     * @param vehicle The vehicle.
     */
    @Override
    public void vehicleExited(CarPark carPark, String vehicle) {
        lightOn = false;
    }

    /**
     * Sets the state of the light once it is associated with a car park.
     *
     * @param carPark The car park.
     */
    @Override
    public void listenerRegistered(CarPark carPark) {
        lightOn = carPark.getFreeSpaces() == 0;
    }

    /**
     * Checks whether the light is switched on.
     *
     * @return True if the light is on, false otherwise.
     */
    public boolean isLightOn() {
        return lightOn;
    }

    /**
     * String representation of light state.
     *
     * @return Description.
     */
    public String getDescription() {
        return lightOn ? "On" : "Off";
    }
}
