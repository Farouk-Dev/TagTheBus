package com.example.faroukjabberi.tagthebus.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by farouk.jabberi on 12/12/2017.
 */

public class StationPicture extends RealmObject implements Parcelable {

    private  int id ;
    private String picture;
    private  String date ;
    private  String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "StationPicture{" +
                "id=" + id +
                ", picture='" + picture + '\'' +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.picture);
        dest.writeString(this.date);
        dest.writeString(this.title);
    }

    public StationPicture() {
    }

    protected StationPicture(Parcel in) {
        this.id = in.readInt();
        this.picture = in.readString();
        this.date = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<StationPicture> CREATOR = new Parcelable.Creator<StationPicture>() {
        @Override
        public StationPicture createFromParcel(Parcel source) {
            return new StationPicture(source);
        }

        @Override
        public StationPicture[] newArray(int size) {
            return new StationPicture[size];
        }
    };
}
