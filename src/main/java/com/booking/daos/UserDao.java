package com.booking.daos;

import com.booking.models.Location;
import com.booking.models.User;

public interface UserDao {
    void addUser(User user);

    User retrieveUser(String username);

    void updateUserLocation(String username, Location location);

    Location retrieveUserLocation(String username);

    User updateUser(String username, User user);

    Boolean isUserOnRide(String username);
}
