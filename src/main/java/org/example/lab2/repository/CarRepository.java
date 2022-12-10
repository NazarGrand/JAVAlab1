package org.example.lab2.repository;

import org.example.lab2.model.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarRepository {
    private final DemoData demoData = new DemoData();

    public CarRepository() {
        demoData.createDemoData();
    }

    public List<Car> findAll() {
        return demoData.getCars();
    }

    public List<Car> findByBrand(String brand) {
        return demoData.getCars().stream()
                .filter(c -> c.getBrand().equals(brand))
                .collect(Collectors.toList());
    }
}
