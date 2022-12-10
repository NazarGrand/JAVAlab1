package service;


import org.example.lab2.model.Car;
import org.example.lab2.repository.CarRepository;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CarService {
    private final CarRepository carRepository;

    public CarService() {
        this.carRepository = new CarRepository();
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> findByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    public Set<Car> sort(List<Car> cars) {
        return new TreeSet<>(cars);
    }

}
