package com.devfactory.drivingpartner.app.guidance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devfactory.drivingpartner.R;
import com.devfactory.drivingpartner.app.seach.SearchListItem;
import com.devfactory.drivingpartner.model.LoadLocation;

import java.util.ArrayList;

/**
 * Created by choiyejin on 16. 8. 22..
 */
public class GuidanceRecyclerAdapter extends RecyclerView.Adapter<GuidanceRecyclerAdapter.AppViewHolder> {

    private ArrayList<LoadLocation> arrayList;

    public GuidanceRecyclerAdapter(ArrayList<LoadLocation> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_guidane, parent, false);
        return new AppViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GuidanceRecyclerAdapter.AppViewHolder holder, int position) {
        holder.mtextViewTitle.setText(arrayList.get(position).getTourName());
        holder.mtextViewAccount.setText(arrayList.get(position).getAddress());
        holder.mtextViewTime.setText(Integer.toString(arrayList.get(position).getImageID()));
        holder.mtextViewKm.setText(Integer.toString(arrayList.get(position).getNumber()));
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
        protected TextView mtextViewTitle;
        protected TextView mtextViewAccount;
        protected TextView mtextViewTime;
        protected TextView mtextViewKm;

        public AppViewHolder(View itemView) {
            super(itemView);

            mtextViewTitle = (TextView) itemView.findViewById(R.id.title_text_guidance);
            mtextViewAccount = (TextView) itemView.findViewById(R.id.account_text_guidance);
            mtextViewTime = (TextView) itemView.findViewById(R.id.time_text_guidance);
            mtextViewKm = (TextView) itemView.findViewById(R.id.km_text_guidance);
        }

    }
}
