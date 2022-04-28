package com.booking.services.impl;

import com.booking.daos.RideDao;
import com.booking.daos.impl.RideDaoImpl;
import com.booking.models.Driver;
import com.booking.models.Location;
import com.booking.models.Ride;
import com.booking.models.User;
import com.booking.services.DriverService;
import com.booking.services.RideService;
import com.booking.services.UserService;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RideServiceImpl implements RideService {

    static Logger logger = null;
    private DriverService driverService;
    private UserService userService;
    private RideDao rideDao;

    public RideServiceImpl() {
        logger = Logger.getLogger(this.getClass()
                .getName());
        this.driverService = new DriverServiceImpl();
        this.userService = new UserServiceImpl();
        this.rideDao = new RideDaoImpl();
    }

    @Override
    public List<Driver> findRide(String username, Location source, Location destination) {
        logger.info(String.format("Finding rides having max distance 5 units for user: %s.", username));
        List<Driver> allDriver = driverService.findAllDriver();

        // retrieve list of drivers that are
        // available and is near to user's departure location
        List<Driver> drivers = allDriver.parallelStream()
                .filter(driver -> Boolean.TRUE.equals(driver.isAvailable()))
                .filter(driver -> isMax5UnitDistance(driver, source))
                .collect(Collectors.toList());

        if (!drivers.isEmpty())
            createRide(username, source, destination);

        logger.info(String.format("Available drivers:[ %s ]", drivers.stream()
                .map(driver -> driver.getUser()
                        .getUserName())
                .collect(Collectors.joining(", "))));

        return drivers;
    }

    /**
     * add ride details to database
     *
     * @param username
     * @param source
     * @param destination
     */
    private void createRide(String username, Location source, Location destination) {
        User user = userService.retrieveUser(username);
        Ride ride = new Ride(user, source, destination);

        rideDao.createRide(ride);
    }

    private boolean isMax5UnitDistance(Driver driver, Location userLocation) {
        return isMax5UnitDistance(driver.getLocation(), userLocation);
    }

    /**
     * check if distance between given location is
     * less than 5 or not
     *
     * @param driverLocation
     * @param userLocation
     * @return
     */
    private boolean isMax5UnitDistance(Location driverLocation, Location userLocation) {
        int x = driverLocation.getX() - userLocation.getX();
        int y = driverLocation.getY() - userLocation.getY();

        return Math.sqrt((x * x) + (y * y)) <= 5;
    }

    @Override
    public void chooseRide(String username, String driverUsername) {
        Location driverLocation = driverService.retrieveDriverLocation(driverUsername);
        Location userLocation = userService.retrieveUserLocation(username);
        Driver driver = driverService.findDriver(driverUsername);

        verifyRide(username, driverUsername);

        // check distance between drivers and user source location
        boolean isMax5UnitDistance = isMax5UnitDistance(driverLocation, userLocation);
        if (isMax5UnitDistance) {
            Ride ride = rideDao.retrieveRide(username);
            driver.setAvailable(false);
            ride.setDriver(driver);
            ride.setStarted(true);

            rideDao.updateRide(username, ride);
        } else {
            throw new IllegalStateException("Can not choose this ride because distance between you and driver is more than 5 unit");
        }
    }

    public void verifyRide(String username, String driverUserName) {

        // if driver is already on other ride
        if (!driverService.isDriverAvailable(driverUserName))
            throw new IllegalStateException(String.format("%s can not choose %s as it is not available.", username, driverUserName));


        // if user is already on other ride
        if (userService.isUserOnRide(username))
            throw new IllegalStateException(String.format("%s can not choose ride as it is on other ride.", username));

    }

    @Override
    public void calculateBill(String username) {

        Ride ride = rideDao.retrieveRide(username);
        // complete ride
        ride.setEnded(true);

        // modify driver location
        driverService.modifyDriverLocation(ride.getDriver()
                .getUser()
                .getUserName(), ride.getDestination());
        // modify driver status
        driverService.modifyDriverStatus(ride.getDriver()
                .getUser()
                .getUserName(), true);
        // modify user location
        userService.modifyUserLocation(ride.getUser()
                .getUserName(), ride.getDestination());
        // calculate bill
        int bill = calculateBill(ride);
        ride.setEarning(bill);
        rideDao.completedRide(ride);

    }

    /**
     * calculate bill for given ride
     *
     * @param ride
     * @return
     */
    private int calculateBill(Ride ride) {
        Location destinationLocation = ride.getDestination();
        Location sourceLocation = ride.getSource();
        int x = destinationLocation.getX() - sourceLocation.getX();
        int y = destinationLocation.getY() - sourceLocation.getY();

        // bill = sqrt( (x1-x2)^2 + (y1-y2)^2 )
        return Math.toIntExact(Math.round(Math.sqrt((x * x) + (y * y))));
    }
}
