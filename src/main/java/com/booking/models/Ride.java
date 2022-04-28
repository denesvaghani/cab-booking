package com.booking.models;

public class Ride {

    private Location source;
    private Location destination;
    private int earning;
    private Driver driver;
    private User user;
    private boolean isEnded;
    private boolean isStarted;

    public Ride(User user, Location source, Location destination) {
        this.user = user;
        this.source = source;
        this.destination = destination;
        this.isStarted = false;
        this.isEnded = false;
    }

    public Ride(Driver driver, User user, Location source, Location destination, int earning) {
        this.driver = driver;
        this.user = user;
        this.source = source;
        this.destination = destination;
        this.earning = earning;
        this.isStarted = true;
        this.isEnded = false;
    }

    public Location getSource() {
        return source;
    }

    public void setSource(Location source) {
        this.source = source;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public int getEarning() {
        return earning;
    }

    public void setEarning(int earning) {
        this.earning = earning;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public void setEnded(boolean ended) {
        isEnded = ended;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "source=" + source +
                ", destination=" + destination +
                ", earning=" + earning +
                ", driver=" + driver +
                ", user=" + user +
                ", isEnded=" + isEnded +
                ", isStarted=" + isStarted +
                '}';
    }
}
