package com.github.cb2222124.carpark;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases covering CarPark, CarParkFullException and CarParkStateException.
 */
public class CarParkTest {

    CarParkTestUtils carParkTestUtils = new CarParkTestUtils();

    /**
     * Given a vehicle is trying to enter a car park, when the car park capacity has space available,
     * then the vehicle should be added.
     */
    @Test
    public void addVehicle() {
        assertDoesNotThrow(() -> {
            CarPark carPark = new CarPark(1);
            carPark.addVehicle("ABC123");
            assertTrue(carPark.containsVehicle("ABC123"));
        });
    }

    /**
     * Given a vehicle is trying to exit a car park, when the vehicle is present in the car park,
     * then the vehicle should be removed.
     */
    @Test
    public void removeVehicle() {
        assertDoesNotThrow(() -> {
            CarPark carPark = carParkTestUtils.createCarPark(1, "ABC123");
            carPark.removeVehicle("ABC123");
            assertFalse(carPark.containsVehicle("ABC123"));
        });
    }

    /**
     * Given a new car park is being created, when the provided vehicle set exceeds the capacity, then a
     * CarParkFullException should be thrown.
     */
    @Test
    public void throwsIfNewCarParkExceedsCapacity() { //Expression lambda
        CarParkFullException exception = assertThrows(CarParkFullException.class,
                () -> carParkTestUtils.createCarPark(1, "ABC123", "XYZ789"));
        assertEquals("Vehicle set exceeds car park capacity", exception.getMessage());
    }

    /**
     * Given a vehicle is trying to be added to a car park, when the car park capacity would be exceeded,
     * then a CarParkFullException should be thrown.
     */
    @Test
    public void throwsIfAddingVehicleExceedsCapacity() { //Statement lambda
        CarParkFullException exception = assertThrows(CarParkFullException.class, () -> {
            CarPark carPark = carParkTestUtils.createCarPark(1, "ABC123");
            carPark.addVehicle("XYZ789");
        });
        assertEquals("Car park capacity reached", exception.getMessage());
    }

    /**
     * Given a vehicle is trying to be added to a car park, when the vehicle registration is already present,
     * then a CarParkStateException should be thrown.
     */
    @Test
    public void throwsWhenAddingVehicleThatAlreadyExists() {
        CarParkStateException exception = assertThrows(CarParkStateException.class, () -> {
            CarPark carPark = carParkTestUtils.createCarPark(2, "ABC123");
            carPark.addVehicle("ABC123");
        });
        assertEquals("Vehicle registration already present", exception.getMessage());
    }

    /**
     * Given a vehicle is trying to be removed from a car park, when the vehicle registration is not present,
     * then a CarParkStateException should be thrown.
     */
    @Test
    public void throwsWhenRemovingVehicleThatDoesNotExist() {
        CarParkStateException exception = assertThrows(CarParkStateException.class, () -> {
            CarPark carPark = carParkTestUtils.createCarPark(1, "ABC123");
            carPark.removeVehicle("XYZ789");
        });
        assertEquals("Vehicle registration not present", exception.getMessage());
    }
}
