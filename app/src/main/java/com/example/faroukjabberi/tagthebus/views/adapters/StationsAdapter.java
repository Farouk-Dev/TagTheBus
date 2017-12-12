package com.example.faroukjabberi.tagthebus.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faroukjabberi.tagthebus.R;
import com.example.faroukjabberi.tagthebus.models.Station;
import com.example.faroukjabberi.tagthebus.views.holders.StationHolder;
import java.util.ArrayList;



/**
 * The type Event adapter.
 */
public class StationsAdapter extends RecyclerView.Adapter<StationHolder> {

    private ArrayList<Station> stations;
    private Context mContext;


    /**
     * Instantiates a new Event adapter.
     *  @param stations             the events
     * @param mContext                     the m context
     */
    public StationsAdapter( ArrayList<Station> stations, Context mContext) {
        this.stations = stations;
        this.mContext = mContext;
    }

    /**
     * Gets visible objects.
     *
     * @return the visible objects
     */
    public ArrayList<Station> getVisibleObjects() {
        return stations;
    }

    @Override
    public StationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_station, parent, false);
        return new StationHolder(itemView, mContext);
    }


    @Override
    public void onBindViewHolder(final StationHolder holder, final int position) {
        holder.bindViewData(stations.get(position));
    }
    @Override
    public int getItemCount() {
        return stations.size();
    }

}
