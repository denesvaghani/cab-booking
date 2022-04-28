package com.booking.services.impl;

import com.booking.daos.UserDao;
import com.booking.daos.impl.UserDaoImpl;
import com.booking.models.GENDER;
import com.booking.models.Location;
import com.booking.models.User;
import com.booking.services.UserService;

import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    static Logger logger = null;

    public UserServiceImpl() {
        logger = Logger.getLogger(this.getClass()
                .getName());
        this.userDao = new UserDaoImpl();
    }

    @Override
    public void modifyUserDetails(String username, User user) {

        // if updated user and current username
        // not matching
        // then throw exception
        if (!user.getUserName()
                .equals(username))
            throw new IllegalStateException("Username can not be changed.");

        // else update user details
        userDao.updateUser(username, user);
    }

    @Override
    public void modifyUserLocation(String username, Location location) {
        logger.info(String.format("Modifying %s of user %s.", location, username));
        userDao.updateUserLocation(username, location);
    }

    @Override
    public void addUser(String username, GENDER gender, int age) {
        logger.info(String.format("Persisting user: %s.", username));
        User user = new User(username, gender, age);
        userDao.addUser(user);
    }

    @Override
    public User retrieveUser(String username) {
        logger.info(String.format("Retrieving user: %s.", username));
        return userDao.retrieveUser(username);
    }

    @Override
    public Location retrieveUserLocation(String username) {
        logger.info(String.format("Retrieving current location of user: %s.", username));
        return userDao.retrieveUserLocation(username);
    }

    @Override
    public Boolean isUserOnRide(String username) {
        logger.info(String.format("Retrieving user: %s is on ride or not.", username));
        return userDao.isUserOnRide(username);
    }
}
