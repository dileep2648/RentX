package model;

public class Bike extends Vehicle {
    public Bike(String id, String brand, String model, double rate, boolean available) {
        super(id, brand, model, rate, available);
    }

    @Override public String getType() { return "BIKE"; }
    @Override public double calculateCost(int days) { return getDailyRate() * days; }
}
