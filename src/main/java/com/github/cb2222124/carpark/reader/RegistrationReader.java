package com.github.cb2222124.carpark.reader;

import java.util.Scanner;

/**
 * Responsible for reading the registration of a vehicle.
 */
public class RegistrationReader extends Reader {

    /**
     * Reads the registration of a vehicle.
     *
     * @return Vehicle registration.
     */
    @Override
    public String read() {
        System.out.println("\nSimulating registration reader, enter vehicle registration: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
