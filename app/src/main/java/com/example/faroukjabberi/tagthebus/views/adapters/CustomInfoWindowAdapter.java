package com.example.faroukjabberi.tagthebus.views.adapters;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faroukjabberi.tagthebus.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by farouk.jabberi on 11/12/2017.
 */

public class CustomInfoWindowAdapter implements   GoogleMap.InfoWindowAdapter {
    private Activity context;

    public CustomInfoWindowAdapter(Activity context){
        this.context = context;
    }
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }
    @Override
    public View getInfoContents(Marker marker) {
        View view = context.getLayoutInflater().inflate(R.layout.custom_info_window, null);
        TextView stationName = (TextView) view.findViewById(R.id.station_textView);
        ImageView arrow = (ImageView) view.findViewById(R.id.arrow_image);
        stationName.setText(marker.getTitle());
        arrow.setVisibility(stationName.getText().toString().equals(context.getString(R.string.your_location))?View.GONE:View.VISIBLE);
        view.setClickable(!stationName.getText().toString().equals(context.getString(R.string.your_location)));
        return view;
    }
}
