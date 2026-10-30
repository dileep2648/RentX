package model;

public class Car extends Vehicle {
    public Car(String id, String brand, String model, double rate, boolean available) {
        super(id, brand, model, rate, available);
    }

    @Override public String getType() { return "CAR"; }
    @Override public double calculateCost(int days) { return getDailyRate() * days; }
}
