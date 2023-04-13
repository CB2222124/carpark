package com.github.cb2222124.carpark.barrier;

import com.github.cb2222124.carpark.CarPark;
import com.github.cb2222124.carpark.CarParkFullException;
import com.github.cb2222124.carpark.CarParkStateException;
import com.github.cb2222124.carpark.reader.Reader;

/**
 * Responsible for processing vehicles.
 */
public abstract class Barrier {

    private final CarPark carPark;
    private final Reader reader;

    //Implementation details of switching the light on and off have
    //been abstracted to a boolean field for this demonstration.
    private boolean isOpen;

    /**
     * Instantiates a barrier with the given associated car park and registration reader.
     *
     * @param carPark The associated car park.
     * @param reader  The registration reader.
     */
    public Barrier(CarPark carPark, Reader reader) {
        this.carPark = carPark;
        this.reader = reader;
    }

    /**
     * Gets the state of the barrier.
     *
     * @return True if the barrier is open, false otherwise.
     */
    @SuppressWarnings("unused")
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Processes vehicles that arrive at the barrier. Subclasses should provide appropriate
     * modification of the car park state once the vehicle has been identified.
     */
    public final void processVehicle() {
        String vehicle = identifyVehicle();
        try {
            updateCarPark(carPark, vehicle);
        } catch (CarParkFullException e) {
            System.out.println("The car park is currently at capacity, we apologise for any inconvenience caused.");
        } catch (CarParkStateException e) {
            //In a real-world application, driver should be informed of the exceptions nature and what actions to take.
            System.err.println(e.getMessage());
        }
        openBarrier();
        closeBarrier();
    }

    /**
     * Opens the barrier
     */
    private void openBarrier() {
        //In a real-world application this would yield until some operation completed.
        isOpen = true;
    }

    /**
     * Closes the barrier.
     */
    private void closeBarrier() {
        //In a real-world application this would yield until some operation completed.
        isOpen = false;
    }

    /**
     * Retrieves the registration of the vehicle present.
     *
     * @return Vehicle registration.
     */
    private String identifyVehicle() {
        return reader.read();
    }

    /**
     * Subclasses should update the state of the car park after the vehicle is processed.
     *
     * @param carPark The car park.
     * @param vehicle The vehicle registration.
     */
    abstract void updateCarPark(CarPark carPark, String vehicle) throws CarParkStateException;
}
