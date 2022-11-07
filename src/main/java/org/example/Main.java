package org.example;

import org.example.lab2.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Driver bmwDriver = new Driver.Builder()
                .fullName("Ткач А.В.")
                .yearOfBirth(1985)
                .retired(false)
                .driverLicenseYear(15)
                .build();

        SportCar car = new SportCar.Builder()
                .brand("BMW")
                .carClass("C")
                .weight(5000)
                .driver(bmwDriver)
                .cofForFuel(0.005)
                .speed(120)
                .build();

        Driver TruckDriver = new Driver.Builder()
                .fullName("Кравченко І.В.")
                .yearOfBirth(1966)
                .retired(false)
                .driverLicenseYear(30)
                .build();

        Truck truck = new Truck.Builder()
                .brand("Грузовик")
                .carClass("D")
                .weight(8000)
                .driver(TruckDriver)
                .cofForFuel(0.002)
                .liftingCapacity(70)
                .build();


        List<Vehicle> vehicles =new ArrayList() {
            {
                add(car);
                add(truck);
            }};

        Driver BusDriver = new Driver.Builder()
                .fullName("Шевченко В.В.")
                .yearOfBirth(1977)
                .retired(false)
                .driverLicenseYear(25)
                .build();

        Bus bus = new Bus.Builder()
                .brand("Mercedes")
                .carClass("C")
                .weight(7000)
                .driver(BusDriver)
                .cofForFuel(0.002)
                .seats(30)
                .build();

        vehicles.add(bus);


        for(Vehicle vehicle:vehicles) {
            System.out.println(vehicle);
            System.out.println(vehicle.typeOfFuel());
            System.out.println("Розхід палива: " + vehicle.fuelConsumption() + "\n");
        }

        SportCar car2 = car;
        System.out.println("Equals = " + car.equals(car2));

        boolean hash = car.hashCode() == car2.hashCode();
        System.out.println("HashCodes are equal = " + hash);
    }
}