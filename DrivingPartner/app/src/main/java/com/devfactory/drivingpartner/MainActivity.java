package com.devfactory.drivingpartner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {

    //예제 리스트 (지울것)
    ArrayList<String> arrayList1;

    private RecyclerView mRecyclerView;
    private RelativeLayout mSearchLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 스플레시를 실행한다.
        // 로그인인 페이지 실행도 나중에 여기에 구현할 예정이다.
        startActivity(new Intent(this, SplashActivity.class));

        initData();
        initUi();
    }

    /**
     * initData()
     * 메인 엑티비티에서 사용할 Data를 초기화 하는 메소드
     */
    private void initData() {
        arrayList1 = new ArrayList<>();
        arrayList1.add("인천대공원 > 인하대학교 > 월미도");
        arrayList1.add("관양1동주민센터");
        arrayList1.add("왕십리 이마트 지상주차장");
        arrayList1.add("한양대학교");
    }

    /**
     * initUi()
     * 메인 엑티비티에서 사용할 widget을 초기화 선언하는 메소드
     */
    private void initUi() {

        // Recyclerview 선언
        // 최근 경로나, 자주가는 경로를 보여주는 리스트 이다.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        MainRecyclerAdapter adapter = new MainRecyclerAdapter(arrayList1);
        adapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(adapter);

        mSearchLayout = (RelativeLayout) findViewById(R.id.search_layout_main);
        mSearchLayout.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.search_layout_main:
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
        }
    }
}
