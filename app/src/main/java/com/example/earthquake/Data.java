package com.example.earthquake;

import java.net.URL;

public class Data {
    //creating a decimal value to get the magnitude of the earthquake
    private double mMagnitude;

    //creating a string data type to get the place of the earthquake
    private String mPlace;

    //creating a string data type to get the date of the earthquake
    private long mTimeInMilliSeconds;

    //creating a string to store the url of the earthquake news
    private String mUrlData;

    //initializing the data required by the app using constructor
    public Data(double magnitude, String Place, long DateTime, String Url)
    {
        mMagnitude = magnitude;
        mPlace = Place;
        mTimeInMilliSeconds = DateTime;
        mUrlData = Url;
    }

    //getter methods
    public double getMagnitude(){return mMagnitude;}
    public String getPlace(){return mPlace;}
    public long getTimeInMilliSeconds(){return mTimeInMilliSeconds;}
    public String getURL(){return mUrlData;}
}

