package service;

import exceptions.RentalException;

import java.io.IOException;

public class RentalThread extends Thread {
    private final RentalService service;
    private final String vehicleId;

    public RentalThread(String name, RentalService service, String vehicleId) {
        super(name);
        this.service = service;
        this.vehicleId = vehicleId;
    }

    @Override
    public void run() {
        try {
            service.rentVehicle(vehicleId);
            System.out.println(getName() + " rented " + vehicleId);
        } catch (RentalException | IOException e) {
            System.out.println(getName() + " could not rent it: " + e.getMessage());
        }
    }
}
