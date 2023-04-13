package com.github.cb2222124.carpark;

import com.github.cb2222124.carpark.barrier.EntranceBarrier;
import com.github.cb2222124.carpark.barrier.ExitBarrier;
import com.github.cb2222124.carpark.io.CarParkFileHandler;
import com.github.cb2222124.carpark.io.CarParkReader;
import com.github.cb2222124.carpark.reader.BarcodeReader;
import com.github.cb2222124.carpark.reader.RegistrationReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Car park application demonstration.
 */
public class Main {

    private static Scanner scanner;
    private static CarPark carPark;
    private static CarParkFullSign carParkFullSign;
    private static EntranceBarrier barcodeEntranceBarrier;
    private static EntranceBarrier registrationEntranceBarrier;
    private static ExitBarrier exitBarrier;

    /**
     * Car park application entry point.
     *
     * @param args Arguments are not parsed for this application.
     * @throws IOException            If a critical IO error occurs.
     * @throws ClassNotFoundException If car park deserialization fails.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        scanner = new Scanner(System.in);
        initCarPark();
        setupCarPark();
        startREPL();
    }

    /**
     * Reads a car park from local storage or instantiates a new one. If local data exists, the user will be
     * asked if they want to use it, otherwise the user is asked for the new car parks capacity.
     *
     * @throws IOException            If a critical IO error occurs.
     * @throws ClassNotFoundException If car park deserialization fails.
     */
    private static void initCarPark() throws IOException, ClassNotFoundException {
        try {
            carPark = new CarParkReader().read(new FileInputStream("carpark.dat"));
            System.out.println("Specify application start state: ");
            System.out.println("1) Use persistent car park data");
            System.out.println("2) Use new car park");
            System.out.print("> ");
            if (scanner.nextLine().equals("1")) return;
        } catch (FileNotFoundException e) {
            //Only want to handle expected exception, IO or class resolution failure should be considered critical.
            System.out.println("No car park data could be found. Create a new car park instance...");
        }
        System.out.print("Enter new car park capacity:\n> ");
        int capacity = Integer.parseInt(scanner.nextLine());
        carPark = new CarPark(capacity);
    }

    /**
     * Instantiates change listeners and system-relevant classes.
     */
    private static void setupCarPark() {
        carParkFullSign = new CarParkFullSign();
        carPark.addChangeListener(carParkFullSign);
        carPark.addChangeListener(new CarParkFileHandler());

        barcodeEntranceBarrier = new EntranceBarrier(carPark, new BarcodeReader());
        registrationEntranceBarrier = new EntranceBarrier(carPark, new RegistrationReader());
        exitBarrier = new ExitBarrier(carPark, new RegistrationReader());
    }

    /**
     * REPL (Read-evaluate-print-loop) used to demonstrate car park application functionality.
     */
    private static void startREPL() {
        while (true) {
            System.out.println("\n" + "Current application state:");
            System.out.println("- Full sign: " + carParkFullSign.getDescription());
            System.out.println("- Car park: " + carPark);
            System.out.println("\nSpecify operation number:");
            System.out.println("1) Simulate Barcode entrance barrier");
            System.out.println("2) Simulate Registration entrance barrier");
            System.out.println("3) Simulate Exit barrier");
            System.out.println("4) Exit application");
            System.out.print("> ");
            switch (scanner.nextLine()) {
                case "1" -> barcodeEntranceBarrier.processVehicle();
                case "2" -> registrationEntranceBarrier.processVehicle();
                case "3" -> exitBarrier.processVehicle();
                case "4" -> System.exit(0);
                default -> System.out.println("Invalid input!");
            }
        }
    }
}
