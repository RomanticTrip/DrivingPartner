package com.devfactory.drivingpartner.app.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devfactory.drivingpartner.R;
import com.devfactory.drivingpartner.model.PrivateLocation;

import java.util.ArrayList;

/**
 * Created by choiyejin on 16. 8. 19..
 */
public class MainRecyclerGridAdapter extends RecyclerView.Adapter<MainRecyclerGridAdapter.AppViewHolder> {

    private ArrayList<PrivateLocation> arrayList;

    public MainRecyclerGridAdapter(ArrayList<PrivateLocation> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_grid_fragment_main, parent, false);
        return new AppViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        holder.mtextViewTitle.setText(arrayList.get(position).getTourName());
        holder.mtextViewAddress.setText(arrayList.get(position).getAddress());
        holder.mImageViewBackground.setBackgroundResource(arrayList.get(position).getImageID());
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
        protected TextView mtextViewAddress;
        protected ImageView mImageViewBackground;
        protected ImageView mImageviewIconOn;
        protected ImageView mImageviewIconOff;


        public AppViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;

            mtextViewTitle = (TextView) view.findViewById(R.id.title_text_private_main);
            mtextViewAddress = (TextView) view.findViewById(R.id.address_text_private_main);
            mImageViewBackground = (ImageView) view.findViewById(R.id.image_private_main);
            mImageviewIconOn = (ImageView) view.findViewById(R.id.icon_on_favorites_private_main);
            mImageviewIconOff = (ImageView) view.findViewById(R.id.icon_off_favorites_private_main);

        }

    }
}
