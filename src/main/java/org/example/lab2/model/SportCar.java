package org.example.lab2.model;

/**
 * SportCar успадкований клас від абстрактного класу Vehicle
 * поле speed - швидкість
 */
public class SportCar extends Vehicle{
    private double speed;

    public SportCar(){
        super();
    }
    public SportCar(String producer, String aClass, double weight, Driver driver, double cofForFuel, double speed) {
        super(producer, aClass, weight, driver, cofForFuel);
        this.speed = speed;
    }


    public static class Builder extends Vehicle.Builder<Builder> {

        private double speed;
        public Builder() {
        }

        public Builder speed(double speed) {
            this.speed = speed;
            return  this;
        }

        public SportCar build() {
            return new SportCar(this);
        }
    }

    protected  SportCar(Builder builder){
        super(builder);
        this.speed = builder.speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String typeOfFuel() {
        return "Бензин";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportCar sportCar)) return false;
        if (!super.equals(o)) return false;

        return Double.compare(sportCar.speed, speed) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(speed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "SportCar{" +
                "speed=" + speed +
                super.toString() +  "} ";
    }
}
