package org.example.lab2.run;

import org.example.lab2.model.*;
import org.example.lab2.serialize.JsonMapper;
import org.example.lab2.serialize.TxtMapper;
import org.example.lab2.serialize.XmlMapper;
import service.CarService;
import service.DriverService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RunApp {
    private CarService carService;
    private DriverService driverService;

    public RunApp() {
        carService = new CarService();
        driverService = new DriverService();
    }

    public static void main(String[] args) {
        //testRun();
        //new RunApp().demoServices();
        // new RunApp().demoValidation();
        new RunApp().demoMySql();
    }

    private void demoMySql() {
        Driver bmwDriver = new Driver.Builder()
                .fullName("Ткач А.C.")
                .yearOfBirth(1985)
                .retired(false)
                .driverLicenseYear(15)
                .build();
       // driverService.create(bmwDriver);

        System.out.println("Demo driverService.getAll() ===========================");
        driverService.findAll().forEach(System.out::println);

        System.out.println("\nDemo driverService.findByBirthday(1985) =====================");
        driverService.findByBirthday(1985).forEach(System.out::println);

        System.out.println("\nDemo driverService.findSortByDriverLicenseYear() =====================");
        driverService.findSortByDriverLicenseYear().forEach(System.out::println);


        List<Driver> drivers = driverService.findByBirthday(1985);
        Driver driver = drivers.get(0);
        driver.setFullName("Шевченко А.К");

       driverService.update(driver);
        System.out.println(driver);
        //driverService.deleteDriver(UUID.fromString("29e3de5a-4538-4df1-97dc-3e11daf1970e"));
    }


    private void demoValidation() {
        try {
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

            System.out.println(truck);
        } catch (IllegalArgumentException e) {
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
        carService.sort().forEach(System.out::println);

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
                .weight(2000)
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