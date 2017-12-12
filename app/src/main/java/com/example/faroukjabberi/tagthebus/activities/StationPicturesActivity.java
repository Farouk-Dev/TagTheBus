package com.example.faroukjabberi.tagthebus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faroukjabberi.tagthebus.R;
import com.example.faroukjabberi.tagthebus.events.NavigationEvent;
import com.example.faroukjabberi.tagthebus.events.PictureValidationEvent;
import com.example.faroukjabberi.tagthebus.models.Station;
import com.example.faroukjabberi.tagthebus.models.StationPicture;
import com.example.faroukjabberi.tagthebus.utils.Constants;
import com.example.faroukjabberi.tagthebus.utils.Utils;
import com.example.faroukjabberi.tagthebus.views.adapters.PicturesAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

import static com.example.faroukjabberi.tagthebus.utils.Constants.CAMERA_CAPTURE_IMAGE_REQUEST_CODE;

public class StationPicturesActivity extends AppCompatActivity {
    @BindView(R.id.station_name)
    TextView stationName;
    @BindView(R.id.pictures_recycler)
    RecyclerView recycler;
    private PicturesAdapter picturesAdapter;
    @OnClick(R.id.add_picture)
    void takePicture() {
        Utils.captureImage(this);
    }

    private Station station;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_pictures);
        ButterKnife.bind(this);
        // Get a Realm instance for this thread
        realm = Realm.getDefaultInstance();
        // get data from bundle
        Bundle data = getIntent().getExtras();
        station = data.getParcelable(Constants.STATION);
        stationName.setText(station.getStreet_name());

        // attach an adapter to the  recyclerView********************
        recycler.setLayoutManager(new LinearLayoutManager(this));
        picturesAdapter = new PicturesAdapter(realm.where(StationPicture.class).equalTo("id", Integer.valueOf(station.getId())).findAll(), this);
        recycler.setAdapter(picturesAdapter);
        //****************************************

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                Utils.previewCapturedImage(this, realm, Integer.valueOf(station.getId()));
                //Toast.makeText(getApplicationContext(),intent.getStringExtra("id_station"), Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onPictureValidationEvent(PictureValidationEvent pictureValidationEvent) {
        // refresh  picture's list
        if (pictureValidationEvent.isValidation())
            picturesAdapter.setPictures(realm.where(StationPicture.class).equalTo("id", Integer.valueOf(station.getId())).findAll());
    }
    @Subscribe()
    public  void  onNavigationEvent(NavigationEvent navigationEvent){
        // navigate  to  PictureDetailsActivity
        Utils.navigate(this,PictureDetailsActivity.class,station,navigationEvent.getStationPicture());
    }



}
