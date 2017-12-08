package com.example.faroukjabberi.tagthebus.models;

/**
 * Created by farouk.jabberi on 08/12/2017.
 */

public class NearStations {

    /**
     * code : 200
     * data : {}
     */

    private int code;
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

    public static class Data {
    }
}
