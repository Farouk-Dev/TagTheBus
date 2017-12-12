package com.example.faroukjabberi.tagthebus.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by farouk.jabberi on 08/12/2017.
 */

public class NearStations implements Parcelable {

    /**
     * code : 200
     * data : {}
     */
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private Data data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeParcelable((Parcelable) this.data, flags);
    }

    public NearStations() {
    }

    protected NearStations(Parcel in) {
        this.code = in.readInt();
        this.data = in.readParcelable(Data.class.getClassLoader());
    }

    public static final Parcelable.Creator<NearStations> CREATOR = new Parcelable.Creator<NearStations>() {
        @Override
        public NearStations createFromParcel(Parcel source) {
            return new NearStations(source);
        }

        @Override
        public NearStations[] newArray(int size) {
            return new NearStations[size];
        }
    };
}
