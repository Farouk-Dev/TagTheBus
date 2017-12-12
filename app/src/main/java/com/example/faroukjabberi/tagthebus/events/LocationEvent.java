package com.example.faroukjabberi.tagthebus.events;

/**
 * Created by farouk.jabberi on 11/12/2017.
 */

public class LocationEvent {
    private boolean location;
    private double latitude;
    private double longitude;

    public LocationEvent(boolean location, double latitude, double longitude) {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public boolean isLocation() {
        return location;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
