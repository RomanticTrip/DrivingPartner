package com.devfactory.drivingpartner.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by choiyejin on 16. 8. 22..
 */

public class Location implements Parcelable {
    // 명소나 지역

    private String TourName;
    private int Age;
    private int Sex;
    private int Time;
    private int VitCnt;
    private String Weather;
    private String Week;
    private int Month;
    private double Latitude;
    private double Hardness;
    private String Area;
    private String Address;

    public Location(String tourName, int age, int sex, int time, int vitCnt, String weather, String week, int month, double latitude, double hardness, String area, String address) {
        TourName = tourName;
        Age = age;
        Sex = sex;
        Time = time;
        VitCnt = vitCnt;
        Weather = weather;
        Week = week;
        Month = month;
        Latitude = latitude;
        Hardness = hardness;
        Area = area;
        Address = address;
    }

    public String getTourName() {
        return TourName;
    }

    public int getAge() {
        return Age;
    }

    public int getSex() {
        return Sex;
    }

    public int getTime() {
        return Time;
    }

    public int getVitCnt() {
        return VitCnt;
    }

    public String getWeather() {
        return Weather;
    }

    public String getWeek() {
        return Week;
    }

    public int getMonth() {
        return Month;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getHardness() {
        return Hardness;
    }

    public String getArea() {
        return Area;
    }

    public String getAddress() {
        return Address;
    }

    protected Location(Parcel in) {
        TourName = in.readString();
        Age = in.readInt();
        Sex = in.readInt();
        Time = in.readInt();
        VitCnt = in.readInt();
        Weather = in.readString();
        Week = in.readString();
        Month = in.readInt();
        Latitude = in.readDouble();
        Hardness = in.readDouble();
        Area = in.readString();
        Address = in.readString();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(TourName);
        dest.writeInt(Age);
        dest.writeInt(Sex);
        dest.writeInt(Time);
        dest.writeInt(VitCnt);
        dest.writeString(Weather);
        dest.writeString(Week);
        dest.writeInt(Month);
        dest.writeDouble(Latitude);
        dest.writeDouble(Hardness);
        dest.writeString(Area);
        dest.writeString(Address);
    }
}
