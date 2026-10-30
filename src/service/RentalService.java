package service;

import exceptions.RentalException;
import model.Bike;
import model.Car;
import model.Vehicle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class RentalService {
    private final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private final Path dataFile;

    public RentalService(Path dataFile) throws IOException {
        this.dataFile = dataFile;
        loadVehicles();
    }

private void loadVehicles() throws IOException {
    if (!Files.exists(dataFile)) return;

    for (String line : Files.readAllLines(dataFile)) {
        if (line.isBlank() || line.startsWith("#")) continue;

        StringTokenizer st = new StringTokenizer(line, "|");

        String type = st.nextToken();
        String id = st.nextToken();
        String brand = st.nextToken();
        String model = st.nextToken();
        double rate = Double.parseDouble(st.nextToken());
        boolean available = Boolean.parseBoolean(st.nextToken());

        if (type.equals("CAR"))
            vehicles.add(new Car(id, brand, model, rate, available));
        else if (type.equals("BIKE"))
            vehicles.add(new Bike(id, brand, model, rate, available));
    }
}
     private void saveVehicles() throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("# TYPE|ID|BRAND|MODEL|DAILY_RATE|AVAILABLE");
        for (Vehicle vehicle : vehicles) lines.add(vehicle.toDataLine());
        Files.write(dataFile, lines);
    }

    public ArrayList<Vehicle> getVehicles() { return vehicles; }

    public synchronized Vehicle findVehicle(String id) throws RentalException {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equalsIgnoreCase(id)) return vehicle;
        }
        throw new RentalException("Vehicle not found.");
    }

    public synchronized Vehicle rentVehicle(String id) throws RentalException, IOException {
        Vehicle vehicle = findVehicle(id);
        vehicle.rent();
        saveVehicles();
        return vehicle;
    }

    public synchronized void returnVehicle(String id) throws RentalException, IOException {
        findVehicle(id).returnVehicle();
        saveVehicles();
    }

   
}
