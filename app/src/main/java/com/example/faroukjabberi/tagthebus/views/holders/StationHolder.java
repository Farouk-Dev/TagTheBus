package com.example.faroukjabberi.tagthebus.views.holders;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.faroukjabberi.tagthebus.R;
import com.example.faroukjabberi.tagthebus.activities.PictureDetailsActivity;
import com.example.faroukjabberi.tagthebus.activities.StationPicturesActivity;
import com.example.faroukjabberi.tagthebus.activities.StationsActivity;
import com.example.faroukjabberi.tagthebus.models.Station;
import com.example.faroukjabberi.tagthebus.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    // binding views
    @BindView(R.id.item)
    RelativeLayout item;
    @BindView(R.id.station_textview)
    TextView stationName;



    private Context context;
    private Station station;

    /**
     * Instantiates a new  Account Statement Holder.
     *
     * @param itemView the item view
     * @param context  the context
     */
    public StationHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;

    }

    /**
     * Bind view data.
     *
     * @param station the accountStatement
     */
    public void bindViewData(Station station) {
        this.station = station;
        if (station != null) {
        stationName.setText(station.getStreet_name());
        }

        item.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Utils.navigate(context, StationPicturesActivity.class,station,null);
    }

}
