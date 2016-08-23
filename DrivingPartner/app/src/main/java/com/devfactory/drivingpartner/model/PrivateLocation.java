package com.devfactory.drivingpartner.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.security.interfaces.DSAKey;

/**
 * Created by choiyejin on 16. 8. 23..
 */
public class PrivateLocation implements Parcelable {
    private String TourName;
    private String Address;
    private int ImageID;
    private int Number;
    private int Favorites;

    public PrivateLocation(String tourName, String address, int imageID, int number, int favorites) {
        TourName = tourName;
        Address = address;
        ImageID = imageID;
        Number = number;
        Favorites = favorites;
    }

    public PrivateLocation(Parcel in) {
        TourName = in.readString();
        Address = in.readString();
        ImageID = in.readInt();
        Number = in.readInt();
        Favorites = in.readInt();
    }

    public static final Creator<PrivateLocation> CREATOR = new Creator<PrivateLocation>() {
        @Override
        public PrivateLocation createFromParcel(Parcel in) {
            return new PrivateLocation(in);
        }

        @Override
        public PrivateLocation[] newArray(int size) {
            return new PrivateLocation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(TourName);
        dest.writeString(Address);
        dest.writeInt(ImageID);
        dest.writeInt(Number);
        dest.writeInt(Favorites);
    }

    public String getTourName() {
        return TourName;
    }

    public String getAddress() {
        return Address;
    }

    public int getImageID() {
        return ImageID;
    }

    public int getNumber() {
        return Number;
    }

    public int getFavorites() {
        return Favorites;
    }

    public static Creator<PrivateLocation> getCREATOR() {
        return CREATOR;
    }

    public void setFavorites(int favorites) {
        Favorites = favorites;
    }
}
