package org.example.lab2.repository;

import org.example.lab2.db.MySqlConnection;
import org.example.lab2.exception.ValidationException;
import org.example.lab2.model.Driver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DriverRepository {

    public List<Driver> findAll() {
        final String getAll = "SELECT * FROM driver";
        return findDrivers(getAll);
    }

    public List<Driver> findByBirthday(int yearOfBirth) {
        final String getByYearOfBirth = "SELECT * FROM driver WHERE yearOfBirth = " + yearOfBirth;
        return findDrivers(getByYearOfBirth);
    }

    public List<Driver> findSortByDriverLicenseYear() {
        final String getSortDrivers = "SELECT * FROM driver ORDER BY driverLicenseYear";
        return findDrivers(getSortDrivers);
    }

    private static ArrayList<Driver> findDrivers(String GET_ALL) {
        var drivers = new ArrayList<Driver>();
        try (Connection conn = MySqlConnection.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL);
            while (rs.next()) {
                var driver = new Driver.Builder()
                        .id(UUID.fromString(rs.getString("id")))
                        .fullName(rs.getString("fullName"))
                        .yearOfBirth(rs.getInt("yearOfBirth"))
                        .retired(rs.getBoolean("retired"))
                        .driverLicenseYear(rs.getInt("driverLicenseYear"))
                        .build();
                drivers.add(driver);
            }
        } catch (SQLException | ValidationException e) {
            e.printStackTrace();
        }
        return drivers;
    }


    public Driver create(Driver driver) {
        var id = UUID.randomUUID();
        String ADD_NEW_DRIVER = "INSERT INTO driver(id, fullName, yearOfBirth, retired, driverLicenseYear)" + "values(?,?,?,?,?)";

        try (Connection connection = MySqlConnection.getConnection();
             var statementDriver = connection.prepareStatement(ADD_NEW_DRIVER);
        ) {
            statementDriver.setString(1, id.toString());
            statementDriver.setString(2, driver.getFullName());
            statementDriver.setInt(3, driver.getYearOfBirth());
            statementDriver.setBoolean(4, driver.isRetired());
            statementDriver.setInt(5, driver.getDriverLicenseYear());

            statementDriver.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return driver;
    }

    public Driver update(Driver driver) {
        var id = UUID.randomUUID();
        String UPDATE_DRIVER = "UPDATE driver SET driverLicenseYear=? WHERE id=?";

        try (Connection connection = MySqlConnection.getConnection();
             var statementDriver = connection.prepareStatement(UPDATE_DRIVER);
        ) {
            statementDriver.setString(1, id.toString());
            statementDriver.setString(2, driver.getFullName());
            statementDriver.setInt(3, driver.getYearOfBirth());
            statementDriver.setBoolean(4, driver.isRetired());
            statementDriver.setInt(5, driver.getDriverLicenseYear());

            statementDriver.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return driver;
    }

    public void deleteDriver(UUID id) {
        try (Connection connection = MySqlConnection.getConnection();
        ) {

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
