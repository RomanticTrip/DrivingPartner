package com.devfactory.drivingpartner;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by choiyejin on 16. 8. 20..
 */
public class SearchListAdapter extends ArrayAdapter<SearchListItem> {
    private Context context;
    private ArrayList<SearchListItem> arrayList;
    private int layoutResourceId;

    public SearchListAdapter(Context context, int resource, ArrayList<SearchListItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = objects;
        this.layoutResourceId = resource;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(layoutResourceId, parent, false);
        }

        TextView textView1 = (TextView) view.findViewById(R.id.title_text_lister_search);
        TextView textView2 = (TextView) view.findViewById(R.id.address_text_lister_search);
        TextView textView3 = (TextView) view.findViewById(R.id.distance_text_lister_search);

        textView1.setText(arrayList.get(position).getTitle());
        textView2.setText(arrayList.get(position).getAddress());
        textView3.setText(arrayList.get(position).getDistence());

        return view;
    }

}
