package com.github.cb2222124.carpark;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Car park full sign test cases.
 */
public class CarParkFullSignTest {

    CarParkTestUtils carParkTestUtils = new CarParkTestUtils();

    /**
     * Given a full sign is added to a car park that is full, then the light should be on.
     */
    @Test
    public void onWhenNewCarParkIsFull() {
        assertDoesNotThrow(() -> {
            CarPark carPark = carParkTestUtils.createCarPark(1, "ABC123");
            CarParkFullSign carParkFullSign = new CarParkFullSign();
            carPark.addChangeListener(carParkFullSign);

            assertTrue(carParkFullSign.isLightOn());
        });
    }

    /**
     * Given a full sign is added to a car park that is not full, then the light should be off.
     */
    @Test
    public void offWhenNewCarParkIsNotFull() {
        assertDoesNotThrow(() -> {
            CarPark carPark = carParkTestUtils.createCarPark(1);
            CarParkFullSign carParkFullSign = new CarParkFullSign();
            carPark.addChangeListener(carParkFullSign);

            assertFalse(carParkFullSign.isLightOn());
        });
    }

    /**
     * Given a full sign is added to a car park, when the car park becomes full, then the light should turn on.
     */
    @Test
    public void switchesOnWhenCarParkBecomesFull() {
        assertDoesNotThrow(() -> {
            CarPark carPark = carParkTestUtils.createCarPark(1);
            CarParkFullSign carParkFullSign = new CarParkFullSign();
            carPark.addChangeListener(carParkFullSign);

            assertFalse(carParkFullSign.isLightOn());
            carPark.addVehicle("ABC123");
            assertTrue(carParkFullSign.isLightOn());
        });
    }

    /**
     * Given a full sign is added to a car park, when the car park becomes not full, then the light should turn off.
     */
    @Test
    public void switchesOffWhenCarParkBecomesNotFull() {
        assertDoesNotThrow(() -> {
            CarPark carPark = carParkTestUtils.createCarPark(1, "ABC123");
            CarParkFullSign carParkFullSign = new CarParkFullSign();
            carPark.addChangeListener(carParkFullSign);

            assertTrue(carParkFullSign.isLightOn());
            carPark.removeVehicle("ABC123");
            assertFalse(carParkFullSign.isLightOn());
        });
    }
}
