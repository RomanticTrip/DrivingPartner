package com.devfactory.drivingpartner.app.seach;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by choiyejin on 16. 8. 20..
 */
public class SearchListItem implements Parcelable {
    private String TourName;
    private String address;
    private String distence;

    public SearchListItem(String title, String address, String distence) {
        this.TourName = title;
        this.address = address;
        this.distence = distence;
    }

    protected SearchListItem(Parcel in) {
        TourName = in.readString();
        address = in.readString();
        distence = in.readString();
    }

    public static final Creator<SearchListItem> CREATOR = new Creator<SearchListItem>() {
        @Override
        public SearchListItem createFromParcel(Parcel in) {
            return new SearchListItem(in);
        }

        @Override
        public SearchListItem[] newArray(int size) {
            return new SearchListItem[size];
        }
    };

    public String getTourName() {
        return TourName;
    }

    public String getAddress() {
        return address;
    }

    public String getDistence() {
        return distence;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(TourName);
        dest.writeString(address);
        dest.writeString(distence);
    }
}
