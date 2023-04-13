package com.github.cb2222124.carpark;

/**
 * Checked exception used to indicate that the operation of a car park method call would result in
 * the car park capacity being exceeded.
 */
public class CarParkFullException extends CarParkStateException {

    /**
     * Instantiates a CarParkStateException with the given detail message.
     *
     * @param message String that describes this particular Exception.
     */
    public CarParkFullException(String message) {
        super(message);
    }
}
