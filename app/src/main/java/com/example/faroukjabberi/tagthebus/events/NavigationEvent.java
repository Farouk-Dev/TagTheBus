package com.example.faroukjabberi.tagthebus.events;

import com.example.faroukjabberi.tagthebus.models.Station;
import com.example.faroukjabberi.tagthebus.models.StationPicture;

/**
 * Created by farouk.jabberi on 12/12/2017.
 */

public class NavigationEvent {
    private StationPicture stationPicture;

    public NavigationEvent(StationPicture stationPicture) {
        this.stationPicture = stationPicture;
    }

    public StationPicture getStationPicture() {
        return stationPicture;
    }
}
