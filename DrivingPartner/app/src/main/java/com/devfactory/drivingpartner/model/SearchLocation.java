package com.devfactory.drivingpartner.model;

/**
 * Created by choiyejin on 16. 8. 23..
 */
public class SearchLocation {
    private String TourName;
    private String Address;
    private int km;

    public SearchLocation(String tourName, String address, int km) {
        TourName = tourName;
        Address = address;
        this.km = km;
    }

    public String getTourName() {
        return TourName;
    }

    public String getAddress() {
        return Address;
    }

    public int getKm() {
        return km;
    }
}
