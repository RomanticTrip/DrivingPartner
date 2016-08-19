package com.devfactory.drivingpartner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by choiyejin on 16. 8. 19..
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.AppViewHolder> {

    private ArrayList<String> arrayList;

    public MainRecyclerAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_main, parent, false);
        return new AppViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        holder.mtextView.setText(arrayList.get(position));
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

            mtextView = (TextView) view.findViewById(R.id.text_recycler_main);
        }

        private void setItemClickEvent(int id){
            this.id = id;
        }
    }
}
