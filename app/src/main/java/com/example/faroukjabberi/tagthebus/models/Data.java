package com.example.faroukjabberi.tagthebus.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by farouk.jabberi on 08/12/2017.
 */

public class Data {


    /**
     * nearstations : []
     * transport : bus
     */

    private String transport;
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
}
