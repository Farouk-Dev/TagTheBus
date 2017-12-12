package com.example.faroukjabberi.tagthebus.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by farouk.jabberi on 08/12/2017.
 */

public class Data implements Parcelable {


    /**
     * nearstations : []
     * transport : bus
     */
    @SerializedName("transport")
    private String transport;
    @SerializedName("nearstations")
    private ArrayList<Station> nearstations;

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public ArrayList<Station> getNearstations() {
        return nearstations;
    }

    public void setNearstations(ArrayList<Station> nearstations) {
        this.nearstations = nearstations;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.transport);
        dest.writeTypedList(this.nearstations);
    }

    public Data() {
    }

    protected Data(Parcel in) {
        this.transport = in.readString();
        this.nearstations = in.createTypedArrayList(Station.CREATOR);
    }

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
