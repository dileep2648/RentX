package model;

import exceptions.RentalException;
import interfaces.Rentable;

public abstract class Vehicle implements Rentable {
    private final String id;
    private final String brand;
    private final String model;
    private final double dailyRate;
    private boolean available;

    public Vehicle(String id, String brand, String model, double dailyRate, boolean available) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.dailyRate = dailyRate;
        this.available = available;
    }

    public String getId() { return id; }
    public double getDailyRate() { return dailyRate; }
    public abstract String getType();
    public abstract double calculateCost(int days);

    @Override
    public synchronized void rent() throws RentalException {
        if (!available) throw new RentalException("That vehicle is already rented.");
        available = false;
    }

    @Override
    public synchronized void returnVehicle() throws RentalException {
        if (available) throw new RentalException("That vehicle is not currently rented.");
        available = true;
    }

    @Override
    public synchronized boolean isAvailable() { return available; }

    public String toDataLine() {
        return getType() + "|" + id + "|" + brand + "|" + model + "|" + dailyRate + "|" + available;
    }

    @Override
    public String toString() {
        return id + " - " + getType() + " - " + brand + " " + model + " - $" + dailyRate
                + "/day - " + (isAvailable() ? "Available" : "Rented");
    }
}
