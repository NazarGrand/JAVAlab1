package org.example.lab2.repository;

import org.example.lab2.model.Driver;
import org.example.lab2.model.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoData {

    private List<Car> cars = new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    public void createDemoData() {
        Driver carDriver = new Driver.Builder()
                .fullName("Ткач А.В.")
                .yearOfBirth(1985)
                .retired(false)
                .driverLicenseYear(15)
                .build();

        Car car1 = new Car.Builder()
                .brand("BMW")
                .carClass("C")
                .weight(5000)
                .driver(carDriver)
                .cofForFuel(0.005)
                .speed(120)
                .build();

        Car car2 = new Car.Builder()
                .brand("Mercedes")
                .carClass("B")
                .weight(4000)
                .driver(carDriver)
                .cofForFuel(0.004)
                .speed(110)
                .build();

        Car car3 = new Car.Builder()
                .brand("BMW")
                .carClass("A")
                .weight(6000)
                .driver(carDriver)
                .cofForFuel(0.005)
                .speed(130)
                .build();

        cars = Arrays.asList(car1, car2, car3);
    }
}
