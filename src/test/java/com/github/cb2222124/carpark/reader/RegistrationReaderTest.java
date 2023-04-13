package com.github.cb2222124.carpark.reader;

import com.github.cb2222124.carpark.CarParkTestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Registration reader test cases.
 */
public class RegistrationReaderTest {

    private final CarParkTestUtils carParkTestUtils = new CarParkTestUtils();

    /**
     * Given the user enters a registration, then the resulting registration should be the same.
     */
    @Test
    public void registrationMatchesUserInput() {
        RegistrationReader barcodeReader = new RegistrationReader();
        carParkTestUtils.stubSystemScannerInput("ABC123");
        assertEquals("ABC123", barcodeReader.read());
    }
}
