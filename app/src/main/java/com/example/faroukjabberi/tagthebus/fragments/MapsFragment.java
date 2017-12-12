package com.example.faroukjabberi.tagthebus.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.faroukjabberi.tagthebus.R;
import com.example.faroukjabberi.tagthebus.events.LocationEvent;
import com.example.faroukjabberi.tagthebus.models.NearStations;
import com.example.faroukjabberi.tagthebus.models.Station;
import com.example.faroukjabberi.tagthebus.utils.Constants;
import com.example.faroukjabberi.tagthebus.views.adapters.CustomInfoWindowAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by farouk.jabberi on 09/12/2017.
 */

public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {
    @BindView(R.id.mapView)
    MapView mapView;
    private NearStations nearStations;
    private GoogleMap mMap;

    public static MapsFragment newInstance(NearStations nearStations) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.NEAR_STATIONS, nearStations);
        MapsFragment fragment = new MapsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.maps_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // get the NearStations object
        nearStations = (getArguments() != null && getArguments().getParcelable(Constants.NEAR_STATIONS) != null) ?
                (NearStations) getArguments().getParcelable(Constants.NEAR_STATIONS) :
                null;

        if (nearStations != null) {
            if (mapView != null) {
                mapView.onCreate(null);
                mapView.onResume();
                mapView.getMapAsync(this);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getActivity());
        mMap = googleMap;
        LatLng firstStation = new LatLng(Double.valueOf(nearStations.getData().getNearstations().get(0).getLat()), Double.valueOf(nearStations.getData().getNearstations().get(0).getLon()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstStation, 16));
        for (Station station : nearStations.getData().getNearstations()
                ) {
            LatLng position = new LatLng(Double.valueOf(station.getLat()), Double.valueOf(station.getLon()));
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                    .anchor(0.0f, 1.0f)
                    .title(station.getStreet_name())
                    .position(position)
            );
            // set tag to the marker
            marker.setTag(station);
            //Set Custom InfoWindow Adapter
            CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(getActivity());
            mMap.setInfoWindowAdapter(adapter);

        }

        mMap.setOnInfoWindowClickListener(this);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (!marker.getTitle().equals(getString(R.string.your_location))) {
            Station station = (Station) marker.getTag();
            Toast.makeText(getActivity(), station.getStreet_name() + " clicked  " + marker.getTitle(),
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Subscribe()
    public void onLocationEvent(LocationEvent locationEvent) {
        if (locationEvent.isLocation()) {

            // show my location in map
            LatLng myLocation = new LatLng(locationEvent.getLatitude(), locationEvent.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 16));
            mMap.addMarker(new MarkerOptions()
                    .title(getString(R.string.your_location))
                    .position(myLocation));
            Toast.makeText(getActivity(), String.valueOf(locationEvent.getLatitude()) + " ," + String.valueOf(locationEvent.getLongitude()), Toast.LENGTH_SHORT).show();


        } else {
            LatLng firstStation = new LatLng(Double.valueOf(nearStations.getData().getNearstations().get(0).getLat()), Double.valueOf(nearStations.getData().getNearstations().get(0).getLon()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstStation, 16));
        }
    }


}
