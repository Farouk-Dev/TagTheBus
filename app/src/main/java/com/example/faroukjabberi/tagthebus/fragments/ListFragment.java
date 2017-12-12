package com.example.faroukjabberi.tagthebus.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faroukjabberi.tagthebus.R;
import com.example.faroukjabberi.tagthebus.models.NearStations;
import com.example.faroukjabberi.tagthebus.utils.Constants;
import com.example.faroukjabberi.tagthebus.views.adapters.StationsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by farouk.jabberi on 09/12/2017.
 */
public class ListFragment extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView stationsRecycler;
    private NearStations nearStations;

    public static ListFragment newInstance(NearStations nearStations) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.NEAR_STATIONS, nearStations);
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.list_fragment, container, false);
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

        if(nearStations!=null){

            // attach an adapter to the  recyclerView********************
            stationsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            StationsAdapter stationsAdapter = new StationsAdapter(nearStations.getData().getNearstations(),getActivity());
            stationsRecycler.setAdapter(stationsAdapter);
            //****************************************


        }





    }
}
