package com.github.cb2222124.carpark.io;

import com.github.cb2222124.carpark.CarPark;
import com.github.cb2222124.carpark.CarParkTestUtils;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Car park reader tests.
 */
public class CarParkReaderTest {

    private final CarParkTestUtils carParkTestUtils = new CarParkTestUtils();

    /**
     * Given a car park is serialized, then the deserialized carpark read should have the same capacity and contain
     * the same vehicles.
     */
    @Test
    public void readerMatchesCarPark() {
        assertDoesNotThrow(() -> {
            CarPark expectedCarPark = carParkTestUtils.createCarPark(5, "ABC123", "XYZ789");
            byte[] serializedCarPark = carParkTestUtils.serializeCarPark(expectedCarPark);

            CarParkReader carParkReader = new CarParkReader();
            CarPark carPark = carParkReader.read(new ByteArrayInputStream(serializedCarPark));

            assertEquals(expectedCarPark.getCapacity(), carPark.getCapacity());
            assertEquals(expectedCarPark.getVehicles(), carPark.getVehicles());
        });

    }
}
