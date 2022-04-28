package com.booking.models;

public class Vehicle {
    private String carNumber;
    private String carName;

    public Vehicle(String carName, String carNumber) {
        this.carName = carName;
        this.carNumber = carNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "carNumber='" + carNumber + '\'' +
                ", carName='" + carName + '\'' +
                '}';
    }
}
