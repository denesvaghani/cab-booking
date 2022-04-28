package com.booking.services;

import com.booking.models.Driver;
import com.booking.models.Location;

import java.util.List;

public interface RideService {

    /**
     * retrieve list of drivers who are near to source
     * max distance: 5 units
     *
     * @param username
     * @param source
     * @param destination
     * @return
     */
    List<Driver> findRide(String username, Location source, Location destination);

    /**
     * update driver status to not available
     * start the ride
     *
     * @param username
     * @param driverUsername
     */
    void chooseRide(String username, String driverUsername);

    /**
     * calculate bill for ongoing ride of user
     * update driver status to available
     * end the ride
     *
     * @param username
     */
    void calculateBill(String username);
}
