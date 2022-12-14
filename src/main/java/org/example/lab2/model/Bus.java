package org.example.lab2.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.lab2.exception.ValidationException;
import org.example.lab2.serialize.TxtFormat;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

/**
 * Bus(автобус) успадкований клас від абстрактного класу Vehicle
 * поле seats - кількість місць в автобусі
 */

public class Bus extends Vehicle implements TxtFormat<Bus>, Serializable {
    private static final long serialVersionUID = 1L;

    @Min(value = 10, message = "{Min.seats}")
    @Max(value = 30, message = "{Max.seats}")
    private int seats;

    public Bus(){

    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Bus(@JsonProperty("producer") String producer,
               @JsonProperty("aClass") String aClass,
               @JsonProperty("weight") double weight,
               @JsonProperty("driver") Driver driver,
               @JsonProperty("cofForFuel") double cofForFuel,
               @JsonProperty("seats") int seats) {
        super(producer, aClass, weight, driver, cofForFuel);
        this.seats = seats;
    }


    public static class Builder extends Vehicle.Builder<Builder> {

        private int seats;

        public Builder() {
        }

        public Builder seats(int seats) {
            this.seats = seats;
            return  this;
        }

        public Bus build() {
            return validate();
        }

        private Bus validate() throws IllegalArgumentException {

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Bus bus = new Bus(this);

            Set<ConstraintViolation<Bus>> violations = validator.validate(bus);

            StringBuilder mb = new StringBuilder();

            for (ConstraintViolation<Bus> violation : violations) {
                mb.append("Error for field " + violation.getPropertyPath() + ": '"+ violation.getInvalidValue() + " " + violation.getMessage()).append("\n");
            }

            if (mb.length() > 0) {
                throw new IllegalArgumentException(mb.toString());
            }
            return bus;
        }
    }

    protected  Bus(Builder builder){
        super(builder);
        this.seats = builder.seats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String typeOfFuel() {
        return "Газ";
    }


    @Override
    public String toString() {
        return "Bus{" +
                "seats=" + seats +
                super.toString() + "} ";
    }


    @Override
    public String toStringSerialize() {
        return "brand=" + this.getBrand() +
                ",carClass=" + this.getCarClass() +
                ",weight=" + this.getWeight() +
                ",driverFullName=" + this.getDriver().getFullName() +
                ",driverYearOfBirth=" + this.getDriver().getYearOfBirth() +
                ",driverLicenseYear=" + this.getDriver().getDriverLicenseYear() +
                ",cofForFuel=" + this.getCofForFuel() +
                ",seats=" + this.getSeats() + ",";
    }

    @Override
    public Bus toObject(String string) throws ValidationException {
        String[] str = string.split(",");
        var values = new ArrayList<String>();
        for (String item : str) {
            String[] innerItem = item.split("=");
            if (innerItem.length == 2) {
                values.add(innerItem[1]);
            }
        }
        for (var i :
                values) {
            i.trim();
        }

        Bus bus = new Builder()
                .brand(values.get(0))
                .carClass(values.get(1))
                .weight(Double.parseDouble(values.get(2)))
                .driver(new Driver.Builder()
                        .fullName(values.get(3))
                        .yearOfBirth(Integer.parseInt(values.get(4)))
                        .driverLicenseYear(5)
                        .build())
                .cofForFuel(Double.parseDouble(values.get(6)))
                .seats(Integer.parseInt(values.get(7)))
                .build();

        return bus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bus bus)) return false;

        if(!super.equals(o)) return false;
        return seats == bus.seats;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(seats);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
