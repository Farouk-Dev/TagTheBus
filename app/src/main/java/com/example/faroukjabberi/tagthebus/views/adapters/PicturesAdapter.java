package com.example.faroukjabberi.tagthebus.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faroukjabberi.tagthebus.R;
import com.example.faroukjabberi.tagthebus.models.StationPicture;
import com.example.faroukjabberi.tagthebus.views.holders.PictureHolder;

import io.realm.RealmResults;

/**
 * Created by farouk.jabberi on 12/12/2017.
 */

public class PicturesAdapter extends RecyclerView.Adapter<PictureHolder> {

    private RealmResults<StationPicture> stationPicures;
    private Context mContext;


    /**
     * Instantiates a new Event adapter.
     *  @param stationPicures             the events
     * @param mContext                     the m context
     */
    public PicturesAdapter(RealmResults<StationPicture> stationPicures, Context mContext) {
        this.stationPicures = stationPicures;
        this.mContext = mContext;
    }

    /**
     * Gets visible objects.
     *
     * @return the visible objects
     */
    public RealmResults<StationPicture> getVisibleObjects() {
        return stationPicures;
    }

    @Override
    public PictureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_picture, parent, false);
        return new PictureHolder(itemView, mContext);
    }


    @Override
    public void onBindViewHolder(final PictureHolder holder, final int position) {
        holder.bindViewData(stationPicures.get(position));
    }
    @Override
    public int getItemCount() {
        return stationPicures.size();
    }


    public void setPictures (RealmResults<StationPicture> stationPicures){
        this.stationPicures=stationPicures;
        notifyDataSetChanged();

    }
}

