package com.devfactory.drivingpartner.app.seach;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devfactory.drivingpartner.R;

import java.util.ArrayList;

/**
 * Created by choiyejin on 16. 8. 20..
 */
public class SearchUndermeunAdapter extends RecyclerView.Adapter<SearchUndermeunAdapter.AppViewHolder> {
    private ArrayList<SearchListItem> arrayList;
    View itemView;

    public SearchUndermeunAdapter(ArrayList<SearchListItem> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public SearchUndermeunAdapter.AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_undermenu_search, parent, false);
        return new AppViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchUndermeunAdapter.AppViewHolder holder, int position) {
        holder.mtextView.setText(arrayList.get(position).getTourName());
        holder.setItemClickEvent(position);
    }

    @Override
    public int getItemCount() {
        if(arrayList != null) {
            return arrayList.size();
        } else {
            return 0;
        }
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        protected View view;
        protected TextView mtextView;
        private int id;

        public AppViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;

            mtextView = (TextView) view.findViewById(R.id.aaa);
        }

        private void setItemClickEvent(int id){
            this.id = id;
        }
    }
}
