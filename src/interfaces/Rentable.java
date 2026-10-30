package interfaces;

import exceptions.RentalException;

public interface Rentable {
    void rent() throws RentalException;
    void returnVehicle() throws RentalException;
    boolean isAvailable();
}
