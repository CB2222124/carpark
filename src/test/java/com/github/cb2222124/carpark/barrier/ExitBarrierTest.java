package com.github.cb2222124.carpark.barrier;

import com.github.cb2222124.carpark.CarPark;
import com.github.cb2222124.carpark.CarParkTestUtils;
import com.github.cb2222124.carpark.reader.RegistrationReader;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exit barrier test cases.
 */
public class ExitBarrierTest {

    private final CarParkTestUtils carParkTestUtils = new CarParkTestUtils();

    /**
     * Given a vehicle is present at an exit barrier and present in the car park,
     * then the vehicle should be removed from the car park.
     */
    @Test
    public void vehicleExitsCarPark() {
        assertDoesNotThrow(() -> {
            CarPark carPark = carParkTestUtils.createCarPark(1, "ABC123");
            ExitBarrier exitBarrier = new ExitBarrier(carPark, new RegistrationReader());
            carParkTestUtils.stubSystemScannerInput("ABC123");
            exitBarrier.processVehicle();
            assertFalse(carPark.containsVehicle("ABC123"));
        });
    }

    /**
     * Given a vehicle is present at an exit barrier and the vehicle is not present in the car park,
     * an error should be streamed to the standard error output.
     */
    @Test
    public void printsErrorWhenVehicleDoesNotExist() {
        assertDoesNotThrow(() -> {
            CarPark carPark = new CarPark(1);
            ExitBarrier exitBarrier = new ExitBarrier(carPark, new RegistrationReader());

            carParkTestUtils.stubSystemScannerInput("ABC123");
            ByteArrayOutputStream out = carParkTestUtils.redirectErrorOutput();

            exitBarrier.processVehicle();

            Stream<String> expected = Stream.of("Vehicle registration not present");

            assertLinesMatch(expected, out.toString().lines());
        });
    }
}
