package org.example.lab2.repository;

import org.example.lab2.model.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarRepository {
    private final DemoData demoData = new DemoData();
    private List<Car> cars;

    public CarRepository() {
        demoData.createDemoData();
        cars = demoData.getCars();
    }

    public List<Car> findAll() {
        return cars;
    }

    public List<Car> findByBrand(String brand) {
        return cars.stream()
                .filter(c -> c.getBrand().equals(brand))
                .collect(Collectors.toList());
    }
}
