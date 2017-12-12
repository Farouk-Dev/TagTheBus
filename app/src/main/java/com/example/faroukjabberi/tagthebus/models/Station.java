package com.example.faroukjabberi.tagthebus.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by farouk.jabberi on 08/12/2017.
 */

public class Station implements Parcelable {

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
    @SerializedName("id")
    private String id;
    @SerializedName("street_name")
    private String street_name;
    @SerializedName("city")
    private String city;
    @SerializedName("utm_x")
    private String utm_x;
    @SerializedName("utm_y")
    private String utm_y;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lon")
    private String lon;
    @SerializedName("furniture")
    private String furniture;
    @SerializedName("buses")
    private String buses;
    @SerializedName("distance")
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.street_name);
        dest.writeString(this.city);
        dest.writeString(this.utm_x);
        dest.writeString(this.utm_y);
        dest.writeString(this.lat);
        dest.writeString(this.lon);
        dest.writeString(this.furniture);
        dest.writeString(this.buses);
        dest.writeString(this.distance);
    }

    public Station() {
    }

    protected Station(Parcel in) {
        this.id = in.readString();
        this.street_name = in.readString();
        this.city = in.readString();
        this.utm_x = in.readString();
        this.utm_y = in.readString();
        this.lat = in.readString();
        this.lon = in.readString();
        this.furniture = in.readString();
        this.buses = in.readString();
        this.distance = in.readString();
    }

    public static final Parcelable.Creator<Station> CREATOR = new Parcelable.Creator<Station>() {
        @Override
        public Station createFromParcel(Parcel source) {
            return new Station(source);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };
}
