package service;

import org.example.lab2.model.Driver;
import org.example.lab2.repository.DriverRepository;

import java.util.List;

public class DriverService {
    private final DriverRepository driverRepository;

    public DriverService() {
        this.driverRepository = new DriverRepository();
    }

    public Driver create(Driver driver){
        return driverRepository.create(driver);
    }

    public List<Driver> findAll(){
        return driverRepository.findAll();
    }

    public List<Driver> findByBirthday(int birthday){
        return driverRepository.findByBirthday(birthday);
    }
}
