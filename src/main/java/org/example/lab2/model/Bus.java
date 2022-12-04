package org.example.lab2.model;

/**
 * Bus(автобус) успадкований клас від абстрактного класу Vehicle
 * поле seats - кількість місць в автобусі
 */

public class Bus extends Vehicle{
    private static final long serialVersionUID = 1L;
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
            return new Bus(this);
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
