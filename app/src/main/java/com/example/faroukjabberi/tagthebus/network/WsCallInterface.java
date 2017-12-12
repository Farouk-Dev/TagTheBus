package com.example.faroukjabberi.tagthebus.network;

import com.example.faroukjabberi.tagthebus.models.NearStations;

/**
 * Created by farouk.jabberi on 09/12/2017.
 */

public interface WsCallInterface {

    void onSuccess(NearStations response);

    void onFailure(NearStations response);
}
