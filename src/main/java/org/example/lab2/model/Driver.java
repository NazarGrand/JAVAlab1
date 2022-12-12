package org.example.lab2.model;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import java.util.Set;

/**
 * Driver(водій) - успадкований клас від Person
 * driverLicenseYear - рік отримання прав
 */
public class Driver extends Person {
    @Max(value = 2022, message = "{Max.driverLicenseYear}")
    private int driverLicenseYear;

    public Driver(){

    }
    public Driver(String fullName, int age, boolean retired, int driverLicenseYear) {
        super(fullName, age, retired);
        this.driverLicenseYear = driverLicenseYear;
    }

    public static class Builder extends Person.Builder<Builder> {

        private int driverLicenseYear;

        public Builder() {
        }

        public Builder driverLicenseYear(int driverLicenseYear) {
            this.driverLicenseYear = driverLicenseYear;
            return  this;
        }

        public Driver build() {
            return new Driver(this);
        }

        private void validate() throws IllegalArgumentException {

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Driver driver = new Driver(this);

            Set<ConstraintViolation<Driver>> violations = validator.validate(driver);

            StringBuilder mb = new StringBuilder();

            for (ConstraintViolation<Driver> violation : violations) {
                mb.append("Error for field " + violation.getPropertyPath() + ": '"+ violation.getInvalidValue() + " " + violation.getMessage()).append("\n");
            }

            if (mb.length() > 0) {
                throw new IllegalArgumentException(mb.toString());
            }
        }
    }

    protected Driver(Builder builder){
        super(builder);
        this.driverLicenseYear = builder.driverLicenseYear;
    }


    public int getDriverLicenseYear() {
        return driverLicenseYear;
    }

    public void setDriverLicenseYear(int driverLicenseYear) {
        this.driverLicenseYear = driverLicenseYear;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "Date of issue driving license = " + driverLicenseYear +
                super.toString() + "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver driver)) return false;
        if (!super.equals(o)) return false;

        return driverLicenseYear == driver.driverLicenseYear;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + driverLicenseYear;
    }
}
