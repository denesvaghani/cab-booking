package com.booking.daos;

import com.booking.models.Ride;

import java.util.Set;

public interface RideDao {
    void createRide(Ride ride);

    void updateRide(String username, Ride ride);

    Ride retrieveRide(String username);

    Set<Ride> retrieveCompletedRide(String username);

    void completedRide(Ride ride);

    void deleteRide(String username);
}
