package com.devfactory.drivingpartner.model;

/**
 * Created by choiyejin on 16. 8. 23..
 */
public class LoadLocation {
    private String TourName;
    private String Address;
    private int ImageID;
    private int Number;

    public LoadLocation(String tourName, String address, int imageID, int number) {
        TourName = tourName;
        Address = address;
        ImageID = imageID;
        Number = number;
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

}
