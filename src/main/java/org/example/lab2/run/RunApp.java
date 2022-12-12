package org.example.lab2.run;

import org.example.lab2.model.*;
import org.example.lab2.serialize.JsonMapper;
import org.example.lab2.serialize.TxtMapper;
import org.example.lab2.serialize.XmlMapper;
import service.CarService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RunApp {
    private CarService carService;

    public RunApp() {
        carService = new CarService();
    }

    public static void main(String[] args) {
       // testRun();
        //new RunApp().demoServices();
        new RunApp().demoValidation();
    }

    private void demoValidation() {
        try{
            Driver bmwDriver = new Driver.Builder()
                    .fullName("Ткач А.В.")
                    .yearOfBirth(1985)
                    .retired(false)
                    .driverLicenseYear(15)
                    .build();

            Car car = new Car.Builder()
                    .brand("BMW")
                    .carClass("C")
                    .weight(500)
                    .driver(bmwDriver)
                    .cofForFuel(0.005)
                    .speed(220)
                    .build();

            System.out.println(car);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    private void demoServices() {
        System.out.println("Demo carService.getAll() =====================");
        List<Car> cars = carService.findAll();
        cars.forEach(System.out::println);

        System.out.println("\nDemo carService.findByBrand('BMW') =====================");
        carService.findByBrand("BMW").forEach(System.out::println);

        System.out.println("\nDemo carService.sort(all cars) sort by brand and after weight =====================");
        carService.sort(cars).forEach(System.out::println);

        System.out.println("\nDemo sort by Comparator(all cars) sort by speed and after weight =====================");
        CarComparator carComparator = new CarComparator();
        cars.sort(carComparator);
        cars.forEach(System.out::println);
    }


    private static void testRun() {
        Driver bmwDriver = new Driver.Builder()
                .fullName("Ткач А.В.")
                .yearOfBirth(1985)
                .retired(false)
                .driverLicenseYear(15)
                .build();

        Car car = new Car.Builder()
                .brand("BMW")
                .carClass("C")
                .weight(500)
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

        List<Vehicle> vehicles = new ArrayList() {
            {
                add(car);
                add(truck);
            }
        };

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

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
            System.out.println(vehicle.typeOfFuel());
            System.out.println("Розхід палива: " + vehicle.fuelConsumption() + "\n");
        }

        Car car2 = car;
        System.out.println("Equals = " + car.equals(car2));

        boolean hash = car.hashCode() == car2.hashCode();
        System.out.println("HashCodes are equal = " + hash);

        new JsonMapper<Bus>().writeObject("bus.json", bus);
        new XmlMapper<Bus>().writeObject("bus.xml", bus);
        new TxtMapper<Bus>().writeObject("bus.txt", bus);
    }
}