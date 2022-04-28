package com.booking.models;

import com.booking.models.GENDER;

public class Driver {

    private User user;
    private Vehicle vehicle;
    private Location location;
    private boolean isAvailable;
    public Driver(String userName, GENDER gender, int age, String carName, String carNumber, int x, int y) {
        this.user = new User(userName, gender, age);
        this.vehicle = new Vehicle(carName, carNumber);
        this.location = new Location(x, y);
        this.isAvailable = true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "user=" + user +
                ", vehicle=" + vehicle +
                ", location=" + location +
                '}';
    }
}
