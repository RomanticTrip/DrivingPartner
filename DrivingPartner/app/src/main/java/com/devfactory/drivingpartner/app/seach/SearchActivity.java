package com.devfactory.drivingpartner.app.seach;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import com.devfactory.drivingpartner.R;
import com.devfactory.drivingpartner.model.Location;
import com.devfactory.drivingpartner.model.PrivateLocation;
import com.devfactory.drivingpartner.model.SearchLocation;

import java.util.ArrayList;
import java.util.Locale;

public class SearchActivity extends Activity implements View.OnClickListener{
    private ArrayList<SearchLocation> arraySearchLocation;

    ///////////////UI wedget//////////////////
    ListView mListView;
    EditText mEditText;

    SearchListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initData();

//        Intent intent = getIntent();
//        arrayList = intent.getParcelableArrayListExtra("arrayListSearch");

        mEditText = (EditText) findViewById(R.id.edit_search);

        mListView = (ListView) findViewById(R.id.listView_search);
        adapter = new SearchListAdapter(this, R.layout.item_listview_search, arraySearchLocation);
        mListView.setAdapter(adapter);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String text = mEditText.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        }
    }

    private void initData() {
        arraySearchLocation = new ArrayList<>();
        arraySearchLocation.add(new SearchLocation("차이나타운", "인천광역시 중구 북성동 차이나타운로44번길", 11));
        arraySearchLocation.add(new SearchLocation("송도", "인천광역시연수구송도동", 6));
        arraySearchLocation.add(new SearchLocation("인천대공원'", "인천광역시 남동구 구월1동 예술로 198'", 21));
        arraySearchLocation.add(new SearchLocation("인천 CGV", "인천광역시연수구송도동", 4));
        arraySearchLocation.add(new SearchLocation("인천 신세계 백화점", "인천광역시 남구 구월1동 연남로 35", 35));
        arraySearchLocation.add(new SearchLocation("인천 동화마을", "인천광역시 부평구 부평동 부평대로 206-15", 23));
        arraySearchLocation.add(new SearchLocation("인천대교", "천광역시 중구 항동7가", 4));
        arraySearchLocation.add(new SearchLocation("인천공항", "인천광역시 중구 운서동", 7));
        arraySearchLocation.add(new SearchLocation("소래포구", "인천광역시 남동구 논현동 서해안로 111'", 9));
        arraySearchLocation.add(new SearchLocation("오이도", "경기도 시흥시 정왕동 오이도로 170",6));
        arraySearchLocation.add(new SearchLocation("월곷", "경기도시흥시군자동서해안로", 9));
        arraySearchLocation.add(new SearchLocation("인천 고요남", "인천 계양구 계양4동'", 14));
        arraySearchLocation.add(new SearchLocation("화룽", "인천광역시 남동구 구월1동 구월남로", 7));
    }
}
