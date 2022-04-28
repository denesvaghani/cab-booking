package com.booking.services;

import com.booking.models.GENDER;
import com.booking.models.Location;
import com.booking.models.User;

public interface UserService {

    /**
     * modify user details like age
     *
     * @param username
     * @param user
     */
    void modifyUserDetails(String username, User user);

    /**
     * update user location to given location
     *
     * @param username
     * @param location
     */
    void modifyUserLocation(String username, Location location);

    /**
     * save user to database
     *
     * @param username
     * @param gender
     * @param age
     */
    void addUser(String username, GENDER gender, int age);

    /**
     * retrieve user info. from username
     *
     * @param username
     * @return
     */
    User retrieveUser(String username);

    /**
     * retrieve user location
     *
     * @param username
     * @return
     */
    Location retrieveUserLocation(String username);

    /**
     * return true if user is already on ride
     *
     * @param username
     * @return
     */
    Boolean isUserOnRide(String username);
}
