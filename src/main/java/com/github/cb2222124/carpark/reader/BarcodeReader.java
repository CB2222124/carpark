package com.github.cb2222124.carpark.reader;

import java.util.Scanner;

/**
 * Responsible for reading a barcode and returning the associated vehicle registration.
 */
public class BarcodeReader extends Reader {

    /**
     * Reads a barcode and returns the associated registration.
     *
     * @return Vehicle registration.
     */
    @Override
    public String read() {
        System.out.println("\nSimulating barcode reader, enter vehicle registration: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
