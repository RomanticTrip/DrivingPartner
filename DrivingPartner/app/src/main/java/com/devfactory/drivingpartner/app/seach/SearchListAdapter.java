package com.devfactory.drivingpartner.app.seach;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.devfactory.drivingpartner.R;
import com.devfactory.drivingpartner.model.Location;
import com.devfactory.drivingpartner.model.PrivateLocation;
import com.devfactory.drivingpartner.model.SearchLocation;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by choiyejin on 16. 8. 20..
 */
public class SearchListAdapter extends ArrayAdapter<SearchListItem> {
    private Context context;
    private ArrayList<SearchLocation> arraySeachLoaction;
    private int layoutResourceId;

    private ArrayList<SearchLocation> searchlist = null;

    public SearchListAdapter(Context context, int resource, ArrayList<SearchLocation> objects) {
        super(context, resource);

        this.context = context;
        this.arraySeachLoaction = objects;
        this.layoutResourceId = resource;

        this.searchlist = new ArrayList<>();
        this.searchlist.addAll(arraySeachLoaction);
    }


    @Override
    public int getCount() {
        if(arraySeachLoaction != null) {
            return arraySeachLoaction.size();
        } else {
            return 0;
        }
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

        textView1.setText(arraySeachLoaction.get(position).getTourName());
        textView2.setText(arraySeachLoaction.get(position).getAddress());

        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        arraySeachLoaction.clear();
        if(charText.length() == 0) {
            arraySeachLoaction.addAll(searchlist);
        } else {
            for (SearchLocation item : searchlist) {
                if(item.getTourName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    arraySeachLoaction.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

}
