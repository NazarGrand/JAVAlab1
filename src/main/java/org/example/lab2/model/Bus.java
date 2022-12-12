package org.example.lab2.model;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

/**
 * Bus(автобус) успадкований клас від абстрактного класу Vehicle
 * поле seats - кількість місць в автобусі
 */

public class Bus extends Vehicle{
    private static final long serialVersionUID = 1L;

    @Min(value = 10, message = "{Min.seats}")
    @Max(value = 30, message = "{Max.seats}")
    private int seats;

    public Bus(){

    }

    public Bus(String producer, String aClass, double weight, Driver driver, double cofForFuel, int seats) {
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
            validate();
            return new Bus(this);
        }

        private void validate() throws IllegalArgumentException {

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
        }
    }

    protected  Bus(Builder builder){
        super(builder);
        this.seats = builder.seats;
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
