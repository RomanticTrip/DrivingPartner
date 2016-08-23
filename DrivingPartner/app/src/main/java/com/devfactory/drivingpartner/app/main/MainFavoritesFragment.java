package com.devfactory.drivingpartner.app.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devfactory.drivingpartner.R;
import com.devfactory.drivingpartner.app.seach.SearchListItem;
import com.devfactory.drivingpartner.model.LoadLocation;
import com.devfactory.drivingpartner.model.Location;
import com.devfactory.drivingpartner.model.PrivateLocation;

import java.util.ArrayList;

public class MainFavoritesFragment extends Fragment {

    private Context context;
    private ArrayList<LoadLocation> arrayLoadLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = container.getContext();

        initData();

        return inflater.inflate(R.layout.fragment_main_favorites, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initData() {
        arrayLoadLocation = new ArrayList<>();
    }
}
