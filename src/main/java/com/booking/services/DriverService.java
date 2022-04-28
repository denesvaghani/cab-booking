package com.booking.services;

import com.booking.models.Driver;
import com.booking.models.GENDER;
import com.booking.models.Location;

import java.util.List;

public interface DriverService {

    void modifyDriverLocation(String driverUserName, Location location);

    void modifyDriverStatus(String driverUserName, boolean isAvailable);

    int calculateTotalEarning(String username);

    void calculateTotalEarning();

    void addDriver(String driverName, GENDER gender, int age, String carName, String carNumber, int x, int y);

    List<Driver> findAllDriver();

    Driver findDriver(String username);

    Location retrieveDriverLocation(String username);

    boolean isDriverAvailable(String username);
}
