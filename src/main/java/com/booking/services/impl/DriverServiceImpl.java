package com.booking.services.impl;

import com.booking.daos.DriverDao;
import com.booking.daos.impl.DriverDaoImpl;
import com.booking.models.Driver;
import com.booking.models.GENDER;
import com.booking.models.Location;
import com.booking.services.DriverService;

import java.util.List;
import java.util.logging.Logger;

public class DriverServiceImpl implements DriverService {
    static Logger logger = null;
    private DriverDao driverDao;

    public DriverServiceImpl() {
        logger = Logger.getLogger(this.getClass()
                .getName());
        driverDao = new DriverDaoImpl();
    }

    @Override
    public void modifyDriverLocation(String driverUserName, Location location) {
        driverDao.updateDriverLocation(driverUserName, location);
    }

    @Override
    public void modifyDriverStatus(String driverUserName, boolean isAvailable) {
        driverDao.updateDriverStatus(driverUserName, isAvailable);
    }

    @Override
    public int calculateTotalEarning(String username) {
        logger.info(String.format("Calculating earning for %s driver.", username));
        return driverDao.calculateTotalEarning(username);
    }

    @Override
    public void calculateTotalEarning() {
        logger.info("Calculating earning for each drivers.");

        List<Driver> allDriver = driverDao.findAllDriver();
        allDriver.stream()
                .map(driver -> driver.getUser()
                        .getUserName())
                .forEach(driver -> System.out.printf("%s earn $%d\n", driver, this.calculateTotalEarning(driver)));

    }

    @Override
    public void addDriver(String driverName, GENDER gender, int age, String carName, String carNumber, int x, int y) {
        logger.info(String.format("Persisting driver: %s.", driverName));
        Driver driver = new Driver(driverName, gender, age, carName, carNumber, x, y);
        driverDao.add(driver);
    }

    @Override
    public List<Driver> findAllDriver() {
        logger.info("Retrieving all drivers.");
        return driverDao.findAllDriver();
    }

    @Override
    public Driver findDriver(String username) {
        logger.info(String.format("Retrieving driver: %s details", username));
        return driverDao.retrieveDriver(username);
    }

    @Override
    public Location retrieveDriverLocation(String username) {
        logger.info(String.format("Retrieving driver: %s's location", username));
        return driverDao.retrieveDriverLocation(username);
    }

    @Override
    public boolean isDriverAvailable(String username) {
        logger.info(String.format("Retrieving driver: %s is available or not.", username));
        return driverDao.isDriverAvailable(username);
    }
}
