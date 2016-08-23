package com.devfactory.drivingpartner.app.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devfactory.drivingpartner.R;
import com.devfactory.drivingpartner.app.seach.SearchListItem;
import com.devfactory.drivingpartner.model.Location;

import java.util.ArrayList;

/**
 * Created by choiyejin on 16. 8. 21..
 */
public class MainRecyclerLinearAdapter extends RecyclerView.Adapter<MainRecyclerLinearAdapter.AppViewHolder> {

    private ArrayList<String> arrayList;
    private ArrayList<Integer> arrayList2;

        public MainRecyclerLinearAdapter(ArrayList<String> arrayList, ArrayList<Integer> arrayList2) {
            this.arrayList = arrayList;
            this.arrayList2 = arrayList2;
        }

        @Override
        public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_linear_fragment_main, parent, false);
            return new AppViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(AppViewHolder holder, int position) {
            holder.mtextViewName.setText(arrayList.get(position));
            holder.mbg.setBackgroundResource(arrayList2.get(position));
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
            protected TextView mtextViewName;
            protected LinearLayout mbg;

            private int id;

            public AppViewHolder(View itemView) {
                super(itemView);
                this.view = itemView;

                mtextViewName = (TextView) view.findViewById(R.id.title_text_linear_main);
                mbg = (LinearLayout) view.findViewById(R.id.imgaaa_linear_main);
            }

            private void setItemClickEvent(int id){
                this.id = id;
            }
        }
    }
