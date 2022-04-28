package com.booking.data;

import com.booking.exceptions.AleardyExistsException;
import com.booking.exceptions.NotFoundException;
import com.booking.models.Driver;
import com.booking.models.Location;
import com.booking.models.Ride;
import com.booking.models.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Database {

    private static Database DATABASE;
    private HashMap<String, User> users;
    private HashMap<String, Driver> drivers;
    private HashMap<String, Ride> rides;
    private HashMap<String, Location> userLocations;
    private HashMap<String, Location> driverLocations;
    private HashSet<Ride> completeRides;

    private Database() {
        this.users = new HashMap<>();
        this.drivers = new HashMap<>();
        this.rides = new HashMap<>();
        this.userLocations = new HashMap<>();
        this.driverLocations = new HashMap<>();
        this.completeRides = new HashSet<>();

    }

    public static Database databaseFactory() {
        if (DATABASE == null)
            DATABASE = new Database();

        return DATABASE;
    }

    /**
     * add user details to database
     *
     * @param user
     * @return
     * @throws AleardyExistsException
     */
    public boolean addUser(User user) throws AleardyExistsException {
        boolean isUserPresent = this.users.keySet()
                .stream()
                .filter(username -> username.equals(user.getUserName()))
                .findAny()
                .isPresent();

        // if user is present then
        // throw exception
        if (isUserPresent) {
            throw new AleardyExistsException(String.format("User already register with name %s", user.getUserName()));
        }

        return this.users.put(user.getUserName(), user) != null;
    }

    /**
     * retrieve list of users
     *
     * @return
     */
    public List<User> retrieveUsers() {
        return this.users.values()
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * @param username
     * @throws NotFoundException
     */
    public User retrieveUser(String username) throws NotFoundException {
        User user = this.users.get(username);

        if (Objects.isNull(user)) {
            throw new NotFoundException(String.format("%s not found.", username));
        }

        return user;
    }

    /**
     * return current location of user
     *
     * @param username
     * @return
     * @throws NotFoundException
     */
    public Location retrieveUserLocation(String username) throws NotFoundException {
        Location location = this.userLocations.get(username);

        if (Objects.isNull(location)) {
            throw new NotFoundException(String.format("%s not found.", username));
        }

        return location;
    }

    /**
     * update user location
     *
     * @param username
     * @param location
     */
    public void updateUserLocation(String username, Location location) {
        this.userLocations.put(username, location);
    }

    /**
     * add driver details to database
     *
     * @param driver
     * @return
     * @throws AleardyExistsException
     */
    public boolean addDriver(Driver driver) throws AleardyExistsException {
        boolean isDriverPresent = this.drivers.keySet()
                .stream()
                .filter(username -> username.equals(driver.getUser()
                        .getUserName()))
                .findAny()
                .isPresent();

        if (isDriverPresent) {
            throw new AleardyExistsException(String.format("Driver already register with name %s", driver.getUser()
                    .getUserName()));
        }
        this.driverLocations.put(driver.getUser()
                .getUserName(), driver.getLocation());
        return this.drivers.put(driver.getUser()
                .getUserName(), driver) != null;
    }

    /**
     * retrieve driver detail
     *
     * @param username
     * @return
     * @throws NotFoundException
     */
    public Driver retrieveDriver(String username) throws NotFoundException {
        Driver driver = this.drivers.get(username);

        if (Objects.isNull(driver)) {
            throw new NotFoundException(String.format("%s not found.", username));
        }

        return driver;
    }

    /**
     * retrieve all the drivers
     *
     * @return
     */
    public List<Driver> findAllDriver() {
        return this.drivers.values()
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * retrieve current driver location
     *
     * @param username
     * @return
     * @throws NotFoundException
     */
    public Location retrieveDriverLocation(String username) throws NotFoundException {
        Location location = this.driverLocations.get(username);

        if (Objects.isNull(location)) {
            throw new NotFoundException(String.format("%s not found.", username));
        }

        return location;
    }

    /**
     * update driver location
     *
     * @param username
     * @param location
     */
    public void updateDriverLocation(String username, Location location) {
        this.driverLocations.put(username, location);
        Driver driver = this.drivers.get(username);
        driver.setLocation(location);
        this.drivers.put(username, driver);
    }

    /**
     * add ride details to database
     *
     * @param ride
     */
    public void createRide(Ride ride) {
        this.rides.put(ride.getUser()
                .getUserName(), ride);
    }

    /**
     * @param username
     * @param ride
     */
    public void updateRide(String username, Ride ride) {
        this.rides.put(username, ride);
    }

    /**
     * @param username
     * @throws IllegalStateException
     */
    public Ride retrieveRide(String username) throws IllegalStateException {
        // retrieve ride of given user
        Ride ride = this.rides.get(username);
        if (Objects.isNull(ride)) {
            throw new IllegalStateException(String.format("Ride not found for user %s.", username));
        }

        return ride;
    }

    /**
     * retrieve all the rides.
     */
    public List<Ride> retrieveRides() {
        return this.rides.values()
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * update driver status
     *
     * @param username
     * @param isAvailable
     * @throws IllegalStateException
     */
    public void updateDriverStatus(String username, boolean isAvailable) throws IllegalStateException {
        // retrieve driver detail
        Driver driver = this.drivers.get(username);
        if (Objects.isNull(driver)) {
            throw new IllegalStateException(String.format("%s not found.", username));
        }

        // set availability status
        driver.setAvailable(isAvailable);
        this.drivers.put(driver.getUser()
                .getUserName(), driver);
    }

    /**
     * update user details
     *
     * @param username
     * @param user
     * @throws IllegalStateException
     */
    public User updateUser(String username, User user) throws IllegalStateException {
        // retrieve old user detail
        User oldUser = this.users.get(username);
        if (Objects.isNull(oldUser)) {
            throw new IllegalStateException(String.format("User not found with name %s", username));
        }

        // update user
        return this.users.put(username, user);
    }

    public void deleteRide(String username) {
        this.rides.remove(username);
    }

    public void completeRide(Ride ride) {
        this.completeRides.add(ride);
    }

    public Set<Ride> retrieveCompletedRides() {
        return this.completeRides;
    }

    public Boolean isUserOnRide(String username) {
        return rides.containsKey(username) && rides.get(username)
                .isStarted();
    }

    public Boolean isDriverAvailable(String username) {
        return drivers.get(username)
                .isAvailable();
    }
}
