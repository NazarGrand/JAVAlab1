package service;

import org.example.lab2.model.Driver;
import org.example.lab2.repository.DriverRepository;

import java.util.List;
import java.util.UUID;

public class DriverService {
    private final DriverRepository driverRepository;

    public DriverService() {
        this.driverRepository = new DriverRepository();
    }

    public Driver create(Driver driver) {
        return driverRepository.create(driver);
    }

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public List<Driver> findByBirthday(int birthday) {
        return driverRepository.findByBirthday(birthday);
    }

    public List<Driver> findSortByDriverLicenseYear() {
        return driverRepository.findSortByDriverLicenseYear();
    }

    public Driver update(Driver driver) {
        return driverRepository.update(driver);
    }

    public void deleteDriver(UUID id) {
        driverRepository.deleteDriver(id);
    }
}
