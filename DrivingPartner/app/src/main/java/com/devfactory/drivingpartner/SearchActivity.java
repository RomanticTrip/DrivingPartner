package com.devfactory.drivingpartner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;

import java.util.ArrayList;

public class SearchActivity extends NMapActivity {
    private ArrayList<SearchListItem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        arrayList = new ArrayList<>();
        arrayList.add(new SearchListItem("aaaa", "bbbb", "ccc"));
        arrayList.add(new SearchListItem("bbbb", "cccc", "dddd"));
        arrayList.add(new SearchListItem("dddd", "ffff", "ggg"));
        arrayList.add(new SearchListItem("www", "eeee", "aaaa"));

        ListView listView = (ListView) findViewById(R.id.listView_search);
        SearchListAdapter adapter = new SearchListAdapter(this, R.layout.item_listview_search,arrayList);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
