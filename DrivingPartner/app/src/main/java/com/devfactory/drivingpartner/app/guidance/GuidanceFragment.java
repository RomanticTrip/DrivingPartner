package com.devfactory.drivingpartner.app.guidance;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devfactory.drivingpartner.R;
import com.devfactory.drivingpartner.app.seach.SearchListItem;
import com.devfactory.drivingpartner.model.LoadLocation;

import java.util.ArrayList;

public class GuidanceFragment extends Fragment {

    private Context context;
    private RecyclerView mRecyclerView;
    private ArrayList<LoadLocation> arrayList;

//    public GuidanceFragment(ArrayList<LoadLocation> arrayList) {
//        this.arrayList = arrayList;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = container.getContext();

        initData();

        return inflater.inflate(R.layout.fragment_guidance, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Recyclerview 선언
        mRecyclerView = (RecyclerView) view.findViewById(R.id.route_recyclerview_guidance);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        GuidanceRecyclerAdapter adapter = new GuidanceRecyclerAdapter(arrayList);
        adapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(adapter);
    }

    private void initData() {
        arrayList = new ArrayList<>();
        arrayList.add(new LoadLocation("경로1", "해송십리로(2.9km)-> 제3경인고속화도로(6.3km)-> 경원대로(2.0km)",R.drawable.map1, 1));
        arrayList.add(new LoadLocation("경로2", "해송십리로(2.9km)->제3경인고속화도로(4.1km)-> 앵고개로(1.3km) (1.7km)",R.drawable.map2, 2));
        arrayList.add(new LoadLocation("경로3", "서해안로(6.8km)-> 제3경인고속화도로(6.3km) -> 동부간선도로(2.2km)-> 경원대로(2.0km)",R.drawable.map3, 3));
    }
}
