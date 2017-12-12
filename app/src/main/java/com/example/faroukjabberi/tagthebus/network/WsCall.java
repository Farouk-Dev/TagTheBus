package com.example.faroukjabberi.tagthebus.network;

import com.example.faroukjabberi.tagthebus.models.NearStations;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by farouk.jabberi on 09/12/2017.
 */

public class WsCall {
    private  WsCallInterface wsCallInterface;

    public WsCall(WsCallInterface wsCallInterface) {
        this.wsCallInterface = wsCallInterface;
    }

    public void getStations() {
        RetrofitManager.getInstance("http://barcelonaapi.marcpous.com/bus/nearstation/").getService().getStations().enqueue(new Callback<NearStations>() {
            @Override
            public void onResponse(Call<NearStations> call, Response<NearStations> response) {
                wsCallInterface. onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<NearStations> call, Throwable t) {
                wsCallInterface.onFailure(null);
            }
        });
    }

}
