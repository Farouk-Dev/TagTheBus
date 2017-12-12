package com.example.faroukjabberi.tagthebus.network;

import com.example.faroukjabberi.tagthebus.models.NearStations;
import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by farouk.jabberi on 08/12/2017.
 */

public interface RetrofitServices {
    @GET("latlon/%2041.3985182/2.1917991/1.json")
    Call<NearStations> getStations();

}
