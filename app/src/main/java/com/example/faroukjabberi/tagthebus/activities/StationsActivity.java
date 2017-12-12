package com.example.faroukjabberi.tagthebus.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import com.example.faroukjabberi.tagthebus.R;
import com.example.faroukjabberi.tagthebus.events.LocationEvent;
import com.example.faroukjabberi.tagthebus.fragments.ListFragment;
import com.example.faroukjabberi.tagthebus.fragments.MapsFragment;
import com.example.faroukjabberi.tagthebus.models.NearStations;
import com.example.faroukjabberi.tagthebus.network.WsCall;
import com.example.faroukjabberi.tagthebus.network.WsCallInterface;
import com.example.faroukjabberi.tagthebus.utils.GPS;
import com.example.faroukjabberi.tagthebus.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StationsActivity extends AppCompatActivity implements WsCallInterface, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {
    // binding views *******
    @BindView(R.id.checkbox_location)
    CheckBox locationCheckbox;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private NearStations nearStations;

    //***********************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);
        ButterKnife.bind(this);
        // show the progress Bar
        progressBar.setVisibility(View.VISIBLE);
        // call web service
        new WsCall(this).getStations();
        // set listner to radioGroup
        radioGroup.setOnCheckedChangeListener(this);
        locationCheckbox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onSuccess(NearStations response) {
        // hide the progress Bar
        progressBar.setVisibility(View.GONE);
        if (response != null) {
            nearStations = response;
            // show the radioGroup
            radioGroup.setVisibility(View.VISIBLE);
            // navigate to the list
            Utils.replaceFragment(this, ListFragment.newInstance(nearStations));
        }
    }

    @Override
    public void onFailure(NearStations response) {
        // hide the progress Bar
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.radio_map:
                //navigate to map
                Utils.replaceFragment(this, MapsFragment.newInstance(nearStations));
                locationCheckbox.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_list:
                // navigate to the list
                Utils.replaceFragment(this, ListFragment.newInstance(nearStations));
                locationCheckbox.setVisibility(View.GONE);
                break;


        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            GPS gps = new GPS(this);
            if (gps.canGetLocation()) {
                // send event location
                EventBus.getDefault().post(new LocationEvent(isChecked,gps.getLatitude(),gps.getLongitude()));
            } else {
                gps.showSettingsAlert();
                locationCheckbox.toggle();
            }

        }else{
            EventBus.getDefault().post(new LocationEvent(isChecked, 0,0));
        }
    }
}
