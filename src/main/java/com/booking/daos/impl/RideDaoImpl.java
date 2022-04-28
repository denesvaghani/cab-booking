package com.booking.daos.impl;

import com.booking.daos.RideDao;
import com.booking.data.Database;
import com.booking.models.Ride;

import java.util.Set;

public class RideDaoImpl implements RideDao {
    private Database database;

    public RideDaoImpl() {
        database = Database.databaseFactory();
    }

    @Override
    public void createRide(Ride ride) {
        database.createRide(ride);
    }

    /**
     * update ride details
     *
     * @param username
     * @param ride
     */
    @Override
    public void updateRide(String username, Ride ride) {
        database.updateRide(username, ride);
    }

    @Override
    public Ride retrieveRide(String username) {
        return database.retrieveRide(username);
    }

    @Override
    public Set<Ride> retrieveCompletedRide(String username) {
        return database.retrieveCompletedRides();
    }

    @Override
    public void completedRide(Ride ride) {
        database.completeRide(ride);
        deleteRide(ride.getUser()
                .getUserName());
    }

    @Override
    public void deleteRide(String username) {
        database.deleteRide(username);
    }
}
