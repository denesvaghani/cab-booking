package com.booking.daos.impl;

import com.booking.daos.DriverDao;
import com.booking.data.Database;
import com.booking.models.Driver;
import com.booking.models.Location;

import java.util.List;

public class DriverDaoImpl implements DriverDao {
    private Database database;

    public DriverDaoImpl() {
        database = Database.databaseFactory();
    }

    @Override
    public boolean add(Driver driver) {
        return database.addDriver(driver);
    }

    @Override
    public Driver retrieveDriver(String username) {
        return database.retrieveDriver(username);
    }

    @Override
    public List<Driver> findAllDriver() {
        return database.findAllDriver();
    }

    @Override
    public Location retrieveDriverLocation(String username) {
        return database.retrieveDriverLocation(username);
    }

    @Override
    public void updateDriverLocation(String username, Location location) {
        database.updateDriverLocation(username, location);
    }

    @Override
    public void updateDriverStatus(String userName, boolean isAvailable) {
        database.updateDriverStatus(userName, isAvailable);
    }

    @Override
    public int calculateTotalEarning(String username) {
        return database.retrieveCompletedRides()
                .parallelStream()
                .filter(ride -> ride.getDriver()
                        .getUser()
                        .getUserName()
                        .equals(username) && ride.isEnded() == true)
                .map(ride -> ride.getEarning())
                .reduce(Integer::sum)
                .orElse(0);
    }

    @Override
    public boolean isDriverAvailable(String username) {
        return database.isDriverAvailable(username);
    }
}
