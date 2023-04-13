package com.github.cb2222124.carpark.reader;

/**
 * Responsible for identifying the registration of a vehicle present at a barrier.
 */
public abstract class Reader {

    /**
     * Identifies a vehicle.
     *
     * @return Vehicle registration.
     */
    public abstract String read();
}
