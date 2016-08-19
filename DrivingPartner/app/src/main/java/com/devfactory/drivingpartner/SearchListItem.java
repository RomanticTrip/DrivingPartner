package com.devfactory.drivingpartner;

/**
 * Created by choiyejin on 16. 8. 20..
 */
public class SearchListItem {
    private String title;
    private String address;
    private String distence;

    public SearchListItem(String title, String address, String distence) {
        this.title = title;
        this.address = address;
        this.distence = distence;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getDistence() {
        return distence;
    }
}
