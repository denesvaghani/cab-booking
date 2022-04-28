package com.booking.daos.impl;

import com.booking.daos.UserDao;
import com.booking.data.Database;
import com.booking.models.Location;
import com.booking.models.User;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private Database database;

    public UserDaoImpl() {
        database = Database.databaseFactory();
    }

    @Override
    public void addUser(User user) {
        database.addUser(user);
    }

    @Override
    public List<User> retrieveAllUsers() {
        return database.retrieveUsers();
    }

    @Override
    public User retrieveUser(String username) {
        return database.retrieveUser(username);
    }

    @Override
    public void updateUserLocation(String username, Location location) {
        database.updateUserLocation(username, location);
    }

    @Override
    public Location retrieveUserLocation(String username) {
        return database.retrieveUserLocation(username);
    }

    @Override
    public User updateUser(String username, User user) {
        return database.updateUser(username, user);
    }

    @Override
    public Boolean isUserOnRide(String username) {
        return database.isUserOnRide(username);
    }
}
