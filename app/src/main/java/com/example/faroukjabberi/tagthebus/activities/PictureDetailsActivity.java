package com.example.faroukjabberi.tagthebus.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faroukjabberi.tagthebus.R;
import com.example.faroukjabberi.tagthebus.models.Station;
import com.example.faroukjabberi.tagthebus.models.StationPicture;
import com.example.faroukjabberi.tagthebus.utils.Constants;
import com.example.faroukjabberi.tagthebus.utils.Utils;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PictureDetailsActivity extends AppCompatActivity {
    @BindView(R.id.station_name)
    TextView stationName;
    @BindView(R.id.picture)
    SimpleDraweeView picture;
    @OnClick({R.id.back_image,R.id.back_text})
    void goBack() {
        // go back
        Utils.navigate(this,StationPicturesActivity.class,null,null);
    }




    private Station station;
    private StationPicture stationPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_details);
        ButterKnife.bind(this);

        // get data from bundle
        Bundle data = getIntent().getExtras();
        station = data.getParcelable(Constants.STATION);
        stationPicture = data.getParcelable(Constants.STATION_PICTURE);
        stationName.setText(station.getStreet_name());
        picture.setImageURI(stationPicture.getPicture());
    }
}
