package com.example.faroukjabberi.tagthebus.views.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.faroukjabberi.tagthebus.R;
import com.example.faroukjabberi.tagthebus.events.NavigationEvent;
import com.example.faroukjabberi.tagthebus.models.StationPicture;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by farouk.jabberi on 12/12/2017.
 */

public class PictureHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    // binding views

    @BindView(R.id.picture)
    SimpleDraweeView picture;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.date)
    TextView date;


    private Context context;
    private StationPicture stationPicure;

    /**
     * Instantiates a new  Account Statement Holder.
     *
     * @param itemView the item view
     * @param context  the context
     */
    public PictureHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;

    }

    /**
     * Bind view data.
     *
     * @param stationPicure the accountStatement
     */
    public void bindViewData(StationPicture stationPicure) {
        this.stationPicure = stationPicure;
        if (stationPicure != null) {
            date.setText(stationPicure.getDate() != null ? stationPicure.getDate() : context.getString(R.string.nothing));
            title.setText(stationPicure.getTitle() != null ? stationPicure.getTitle() : context.getString(R.string.nothing));
            picture.setImageURI(stationPicure.getPicture());
        }

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        // send  event  to the activity for the navigation
        EventBus.getDefault().post(new NavigationEvent(stationPicure));
    }
}
