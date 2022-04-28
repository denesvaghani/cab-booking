package com.booking;

import com.booking.models.GENDER;
import com.booking.models.Location;
import com.booking.services.DriverService;
import com.booking.services.RideService;
import com.booking.services.UserService;
import com.booking.services.impl.DriverServiceImpl;
import com.booking.services.impl.RideServiceImpl;
import com.booking.services.impl.UserServiceImpl;

import java.util.logging.Logger;

/**
 * driver class of car booking demo application
 *
 * @author denes vaghani
 */
public class CarBookingDriver {
    static Logger logger = null;
    private DriverService driverService;
    private UserService userService;
    private RideService rideService;

    CarBookingDriver() {
        logger = Logger.getLogger(getClass().getName());
        this.driverService = new DriverServiceImpl();
        this.userService = new UserServiceImpl();
        this.rideService = new RideServiceImpl();
    }

    public static void main(String[] args) {
        CarBookingDriver carBookingDriver = new CarBookingDriver();
        carBookingDriver.registerUsers();
        carBookingDriver.registerDrivers();

        carBookingDriver.rideService.findRide("Abhishek", new Location(0, 0), new Location(20, 1));

        carBookingDriver.rideService.findRide("Rahul", new Location(10, 0), new Location(15, 3));
        carBookingDriver.rideService.chooseRide("Rahul", "Driver1");

        carBookingDriver.rideService.calculateBill("Rahul");

        carBookingDriver.driverService.modifyDriverStatus("Driver1", false);
        carBookingDriver.rideService.findRide("Nandini", new Location(15, 6), new Location(20, 4));

        logger.info("calculating each driver's earning");
        carBookingDriver.driverService.calculateTotalEarning();
    }

    /**
     * register users and their location
     */
    public void registerUsers() {
        logger.info("Registering all users...");
        this.userService.addUser("Abhishek", GENDER.MALE, 23);
        this.userService.modifyUserLocation("Abhishek", new Location(0, 0));

        this.userService.addUser("Rahul", GENDER.MALE, 29);
        this.userService.modifyUserLocation("Rahul", new Location(10, 0));

        this.userService.addUser("Nandini", GENDER.FEMALE, 22);
        this.userService.modifyUserLocation("Nandini", new Location(15, 6));
        logger.info("Successfully registered all users...");
    }

    /**
     * register drivers
     */
    public void registerDrivers() {
        logger.info("Registering all drivers...");
        this.driverService.addDriver("Driver1", GENDER.MALE, 22, "Swift", "KA-01-12345", 10, 1);
        this.driverService.addDriver("Driver2", GENDER.MALE, 29, "Swift", "KA-01-12345", 11, 10);
        this.driverService.addDriver("Driver3", GENDER.MALE, 24, "Swift", "KA-01-12345", 5, 3);
        logger.info("Successfully registered all drivers...");
    }
}
