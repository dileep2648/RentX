# RentX — Vehicle Rental System

RentX is a simple vehicle rental application developed in Java as an academic project. It demonstrates object-oriented programming and fundamental Java concepts through a vehicle rental system.

## Features

* Display available vehicles
* Rent and return vehicles
* Calculate rental costs
* Store vehicle information in a text file
* Demonstrate multithreading using multiple customer threads
* Handle rental-related exceptions

## Java Concepts Demonstrated

* **Inheritance:** Car and Bike inherit from Vehicle.
* **Method Overriding:** Subclasses can provide their own implementations of inherited methods.
* **Interfaces:** Define common behaviour for implementing classes.
* **Collections:** Store and manage vehicle objects.
* **Multithreading:** Simulate multiple customers attempting to rent a vehicle.
* **Exception Handling:** Handle invalid rental operations.
* **Java I/O:** Read vehicle information from a text file.
* **Packages:** Organize classes into logical groups.

## Project Structure

```text
RentX/
└── src/
    ├── data/
    │   └── vehicles.txt
    ├── exceptions/
    ├── interfaces/
    ├── model/
    ├── service/
    └── Main.java
```

## How to Run

1. Install the JDK.
2. Clone or download this repository.
3. Open the project root folder in VS Code.
4. Run `Main.java` using the Java extension.

Ensure the working directory is the project root so the application can locate `src/data/vehicles.txt`.

## Purpose

This project was created to practise Java programming concepts through a small, practical application.
