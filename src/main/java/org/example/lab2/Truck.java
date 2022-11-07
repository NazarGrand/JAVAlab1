package org.example.lab2;

/**
 * Truck(Вантажна машина) успадкований клас від абстрактного класу Vehicle
 * поле liftingCapacity - вантажопідйомність
 */
public class Truck extends Vehicle {
    private double liftingCapacity;

    public Truck(String producer, String aClass, double weight, Driver driver, double cofForFuel, double liftingCapacity) {
        super(producer, aClass, weight, driver, cofForFuel);
        this.liftingCapacity = liftingCapacity;
    }

    public static class Builder extends Vehicle.Builder<Builder> {

        private double liftingCapacity;

        public Builder() {
        }

        public Builder liftingCapacity(double liftingCapacity) {
            this.liftingCapacity = liftingCapacity;
            return  this;
        }

        public Truck build() {
            return new Truck(this);
        }
    }

    protected  Truck(Builder builder){
        super(builder);
        this.liftingCapacity = builder.liftingCapacity;
    }

    public double getLiftingCapacity() {
        return liftingCapacity;
    }

    public void setLiftingCapacity(double liftingCapacity) {
        this.liftingCapacity = liftingCapacity;
    }

    @Override
    public String typeOfFuel() {
     return "Дизель";
    }

    @Override
    public String toString() {
        return "Truck{" +
                "liftingCapacity=" + liftingCapacity +
                 super.toString() + "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Truck truck)) return false;

        if(!super.equals(o)) return false;
        return Double.compare(truck.liftingCapacity, liftingCapacity) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(liftingCapacity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
