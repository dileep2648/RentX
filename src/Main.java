import model.Vehicle;
import service.RentalService;
import service.RentalThread;
import exceptions.RentalException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    
        try  {
            RentalService service = new RentalService(findDataFile());
            boolean running = true;

            while (running) {
                System.out.println("\n--- Vehicle Rental System ---");
                System.out.println("\n1. List vehicles\n2. Rent vehicle\n3. Return vehicle\n4. Thread demo\n0. Exit");
                System.out.print("Choose: ");
                int choice = input.nextInt();
                input.nextLine(); 

                try {
                    switch (choice) {
                        case 1 -> service.getVehicles().forEach(vehicle -> System.out.println(vehicle));
                        case 2 -> rent(input, service);
                        case 3 -> returnVehicle(input, service);
                        case 4 -> threadDemo(service);
                        case 0 -> running = false;
                        default -> System.out.println("Invalid option.");
                    }
                } catch (RentalException | IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read the vehicle file: " + e.getMessage());
        }
    }

   private static Path findDataFile() {
    return Path.of("data/vehicles.txt");
    }

    private static void rent(Scanner input, RentalService service) throws RentalException, IOException {
        System.out.print("Number of days: ");
        int days = input.nextInt();
        input.nextLine(); 
        if (days < 1) {
            System.out.println("Enter at least one day.");
            days = input.nextInt();
            input.nextLine();
        }
        System.out.print("Vehicle ID: ");
        String vehicleId = input.nextLine();
        Vehicle vehicle = service.rentVehicle(vehicleId);
        if (vehicle != null) {
            System.out.println("Vehicle rented: " + vehicle);
            System.out.println("Cost: $" + vehicle.calculateCost(days));
        }

        else {
            System.out.println("Vehicle not found.  Try again.");
        }   
        
    }

    private static void returnVehicle(Scanner input, RentalService service) throws RentalException, IOException {
        System.out.print("Vehicle ID: ");
        String vehicleId = input.nextLine();
        service.returnVehicle(vehicleId);
        System.out.println("Vehicle returned.");
    }

    private static void threadDemo(RentalService service) throws RentalException {
        Vehicle vehicle = service.getVehicles().stream().filter(Vehicle::isAvailable).findFirst()
                .orElseThrow(() -> new RentalException("No vehicles are available."));
        Thread first = new RentalThread("Customer 1", service, vehicle.getId());
        Thread second = new RentalThread("Customer 2", service, vehicle.getId());
        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread demo interrupted.");
        }
    }
}
