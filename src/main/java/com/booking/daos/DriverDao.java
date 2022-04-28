package com.booking.daos;

import com.booking.models.Driver;
import com.booking.models.Location;

import java.util.List;

public interface DriverDao {
    boolean add(Driver driver);

    Driver retrieveDriver(String username);

    List<Driver> findAllDriver();

    Location retrieveDriverLocation(String username);

    void updateDriverLocation(String username, Location location);

    void updateDriverStatus(String userName, boolean isAvailable);

    int calculateTotalEarning(String username);

    boolean isDriverAvailable(String username);
}
