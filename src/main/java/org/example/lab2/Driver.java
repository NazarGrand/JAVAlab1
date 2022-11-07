package org.example.lab2;

/**
 * Driver(водій) - успадкований клас від Person
 * driverLicenseYear - рік отримання прав
 */
public class Driver extends Person {
    private int driverLicenseYear;

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
