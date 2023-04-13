package com.github.cb2222124.carpark.barrier;

import com.github.cb2222124.carpark.CarPark;
import com.github.cb2222124.carpark.CarParkStateException;
import com.github.cb2222124.carpark.reader.Reader;

/**
 * Responsible for processing vehicles attempting to enter the car park.
 */
public class EntranceBarrier extends Barrier {

    /**
     * Instantiates an entrance barrier for the specified car park that uses the specified reader.
     *
     * @param carPark Car park.
     * @param reader  Reader.
     */
    public EntranceBarrier(CarPark carPark, Reader reader) {
        super(carPark, reader);
    }

    /**
     * Adds the given vehicle to the car park.
     *
     * @param carPark The car park.
     * @param vehicle The vehicle registration.
     */
    @Override
    protected void updateCarPark(CarPark carPark, String vehicle) throws CarParkStateException {
        carPark.addVehicle(vehicle);
    }
}
