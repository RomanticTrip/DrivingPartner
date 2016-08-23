package com.devfactory.drivingpartner.app.main;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devfactory.drivingpartner.R;
import com.devfactory.drivingpartner.app.seach.SearchListItem;
import com.devfactory.drivingpartner.model.Location;
import com.devfactory.drivingpartner.model.PrivateLocation;

import java.util.ArrayList;

public class MainPrivateFragment extends Fragment {

    private Context context;
    private RecyclerView mRecyclerView;
    private ArrayList<PrivateLocation> arrayPrivteLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = container.getContext();

        initData();

//        Bundle extras = getArguments();
//        if (extras != null) {
//            this.arrayPrivteLocation = extras.getParcelableArrayList("arrayListSearch");
//        }

        return inflater.inflate(R.layout.fragment_main_private, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Recyclerview 선언
        //recyclerview layout
        //http://blog.sqisland.com/2014/12/recyclerview-grid-with-header.html
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_private_main);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        MainRecyclerGridAdapter adapter = new MainRecyclerGridAdapter(arrayPrivteLocation);
        adapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(adapter);
    }

    // 서버와 연동한후 사라질 메소드
    private void initData() {
        arrayPrivteLocation = new ArrayList<>();
        arrayPrivteLocation.add(new PrivateLocation("차이나타운", "인천광역시 중구 북성동 차이나타운로44번길", R.drawable.location1, 1, 0));
        arrayPrivteLocation.add(new PrivateLocation("송도", "인천광역시연수구송도동", R.drawable.location2, 2, 0));
        arrayPrivteLocation.add(new PrivateLocation("인천대공원'", "인천광역시 남동구 구월1동 예술로 198'", R.drawable.location3, 3, 0));
        arrayPrivteLocation.add(new PrivateLocation("인천 CGV", "인천광역시연수구송도동", R.drawable.location4, 4, 0));
        arrayPrivteLocation.add(new PrivateLocation("인천 신세계 백화점", "인천광역시 남구 구월1동 연남로 35", R.drawable.location5, 5, 0));
        arrayPrivteLocation.add(new PrivateLocation("인천 동화마을", "인천광역시 부평구 부평동 부평대로 206-15", R.drawable.location6, 6, 0));
        arrayPrivteLocation.add(new PrivateLocation("인천대교", "천광역시 중구 항동7가", R.drawable.location7, 7, 0));
        arrayPrivteLocation.add(new PrivateLocation("'인천공항", "인천광역시 중구 운서동", R.drawable.location8, 8, 0));
        arrayPrivteLocation.add(new PrivateLocation("소래포구", "인천광역시 남동구 논현동 서해안로 111'", R.drawable.location9, 9, 0));
        arrayPrivteLocation.add(new PrivateLocation("오이도", "경기도 시흥시 정왕동 오이도로 170", R.drawable.location10, 10, 0));
        arrayPrivteLocation.add(new PrivateLocation("월곷", "경기도시흥시군자동서해안로", R.drawable.location11, 11, 0));
        arrayPrivteLocation.add(new PrivateLocation("인천 고요남", "인천 계양구 계양4동'", R.drawable.location12, 12, 0));
        arrayPrivteLocation.add(new PrivateLocation("화룽", "인천광역시 남동구 구월1동 구월남로", R.drawable.location13, 13, 0));
    }
}
