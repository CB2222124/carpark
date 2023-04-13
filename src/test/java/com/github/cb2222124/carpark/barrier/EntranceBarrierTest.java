package com.github.cb2222124.carpark.barrier;

import com.github.cb2222124.carpark.CarPark;
import com.github.cb2222124.carpark.CarParkTestUtils;
import com.github.cb2222124.carpark.reader.RegistrationReader;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Entrance barrier test cases.
 */
public class EntranceBarrierTest {

    private final CarParkTestUtils carParkTestUtils = new CarParkTestUtils();

    /**
     * Given a vehicle is present at an entrance barrier, when the vehicle isn't already in the car park and the
     * car park isn't full, then the vehicle should be added to the car park.
     */
    @Test
    public void vehicleEntersCarPark() {
        CarPark carPark = new CarPark(1);
        EntranceBarrier entranceBarrier = new EntranceBarrier(carPark, new RegistrationReader());
        carParkTestUtils.stubSystemScannerInput("ABC123");
        entranceBarrier.processVehicle();
        assertTrue(carPark.containsVehicle("ABC123"));
    }

    /**
     * Given a vehicle is present at an entrance barrier, when the car park is full, then the vehicle
     * should not be added and the driver should be informed.
     */
    @Test
    public void vehicleRefusedFullCarPark() {
        CarPark carPark = new CarPark(0);
        EntranceBarrier entranceBarrier = new EntranceBarrier(carPark, new RegistrationReader());

        carParkTestUtils.stubSystemScannerInput("ABC123");
        ByteArrayOutputStream out = carParkTestUtils.redirectStandardOutput();

        entranceBarrier.processVehicle();

        Stream<String> expected = Stream.of(
                "",
                "Simulating registration reader, enter vehicle registration: ",
                "The car park is currently at capacity, we apologise for any inconvenience caused.");

        assertLinesMatch(expected, out.toString().lines());
        assertFalse(carPark.containsVehicle("ABC123"));
    }

    /**
     * Given a vehicle is present at an entrance barrier, when the vehicle is already present in the car park,
     * an error should be streamed to the standard error output.
     */
    @Test
    public void printsErrorWhenVehicleAlreadyExists() {
        assertDoesNotThrow(() -> {
            CarPark carPark = carParkTestUtils.createCarPark(2, "ABC123");
            EntranceBarrier entranceBarrier = new EntranceBarrier(carPark, new RegistrationReader());

            carParkTestUtils.stubSystemScannerInput("ABC123");
            ByteArrayOutputStream out = carParkTestUtils.redirectErrorOutput();

            entranceBarrier.processVehicle();

            Stream<String> expected = Stream.of("Vehicle registration already present");

            assertLinesMatch(expected, out.toString().lines());
        });
    }
}
