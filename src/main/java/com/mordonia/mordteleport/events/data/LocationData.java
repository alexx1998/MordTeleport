package com.mordonia.mordteleport.events.data;

import org.bukkit.Location;

public class LocationData {
    private Location location;
    private Location destination;

    public LocationData(Location location, Location destination){
        this.location = location;
        this.destination = destination;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }
}
