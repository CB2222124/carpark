package com.github.cb2222124.carpark;

/**
 * Checked exception used to indicate that the operation of a car park method call is inappropriate.
 */
public class CarParkStateException extends Exception {

    /**
     * Instantiates a CarParkStateException with the given detail message.
     *
     * @param message String that describes this particular Exception.
     */
    public CarParkStateException(String message) {
        super(message);
    }
}
