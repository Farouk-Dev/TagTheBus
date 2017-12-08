package com.example.faroukjabberi.tagthebus.models;

/**
 * Created by farouk.jabberi on 08/12/2017.
 */

public class Station {

    /**
     * id : 1
     * street_name : Almogàvers-Àvila
     * city : BARCELONA
     * utm_x : 432542,5460
     * utm_y : 4583524,2340
     * lat : 41.3985182
     * lon : 2.1917991
     * furniture : Pal
     * buses : 06 - 40 - 42 - 141 - B25 - N11
     * distance : 0
     */

    private String id;
    private String street_name;
    private String city;
    private String utm_x;
    private String utm_y;
    private String lat;
    private String lon;
    private String furniture;
    private String buses;
    private String distance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUtm_x() {
        return utm_x;
    }

    public void setUtm_x(String utm_x) {
        this.utm_x = utm_x;
    }

    public String getUtm_y() {
        return utm_y;
    }

    public void setUtm_y(String utm_y) {
        this.utm_y = utm_y;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public String getBuses() {
        return buses;
    }

    public void setBuses(String buses) {
        this.buses = buses;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
