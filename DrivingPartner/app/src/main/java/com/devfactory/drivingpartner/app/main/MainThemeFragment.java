package com.devfactory.drivingpartner.app.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devfactory.drivingpartner.R;
import com.devfactory.drivingpartner.app.seach.SearchListItem;
import com.devfactory.drivingpartner.model.Location;

import java.util.ArrayList;


public class MainThemeFragment extends Fragment {

    private Context context;
    private RecyclerView mRecyclerView;
    private ArrayList<String> arrayList;
    private ArrayList<Integer> arrayList2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = container.getContext();

        arrayList = new ArrayList<>();
        arrayList.add("효도");
        arrayList.add("우정");
        arrayList.add("가족여행");
        arrayList.add("힐링");
        arrayList.add("데이트");
        arrayList.add("쇼핑");
        arrayList.add("먹거리");

        arrayList2 = new ArrayList<>();
        arrayList2.add(R.drawable.filial);
        arrayList2.add(R.drawable.friendship);
        arrayList2.add(R.drawable.family);
        arrayList2.add(R.drawable.healing);
        arrayList2.add(R.drawable.date);
        arrayList2.add(R.drawable.shopping);
        arrayList2.add(R.drawable.food);


        return inflater.inflate(R.layout.fragment_main_private, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Recyclerview 선언
        // 최근 경로나, 자주가는 경로를 보여주는 리스트 이다.
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_private_main);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        MainRecyclerLinearAdapter adapter = new MainRecyclerLinearAdapter(arrayList, arrayList2);
        adapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(adapter);
    }

}

