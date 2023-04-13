package com.github.cb2222124.carpark.io;

import com.github.cb2222124.carpark.CarPark;
import com.github.cb2222124.carpark.CarParkTestUtils;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Car park writer tests.
 */
public class CarParkWriterTest {

    private final CarParkTestUtils carParkTestUtils = new CarParkTestUtils();

    /**
     * Given a car park is serialized to a file, then the deserialized car park should match the original.
     */
    @Test
    public void writerMatchesSerializedCarPark() {
        assertDoesNotThrow(() -> {

            CarPark expectedCarPark = carParkTestUtils.createCarPark(5, "ABC123", "XYZ789");

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CarParkWriter carParkReader = new CarParkWriter();
            carParkReader.write(expectedCarPark, out);
            CarPark carPark = carParkTestUtils.deserializeCarPark(out.toByteArray());

            assertEquals(expectedCarPark.getCapacity(), carPark.getCapacity());
            assertEquals(expectedCarPark.getVehicles(), carPark.getVehicles());
        });

    }
}
