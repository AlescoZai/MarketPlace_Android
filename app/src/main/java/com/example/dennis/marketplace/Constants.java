package com.example.dennis.marketplace;

/**
 * Created by dennis on 9/15/17.
 */

public class Constants {
    //Lets now create some variables that will map our data in the market service
    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String API_BASE_URL = "http://api.walmartlabs.com/v1/search?";
    public static final String API_BASE_QUERY = "query";
    public static final String API_KEY_HOLDER = "apiKey";
    public static final String API_BASE_FORMAT = "format";
    public static final String FIREBASE_CHILD_SEARCHED_ITEM = "savedcommodities";
    public static final String FIREBASE_CHILD_ITEM = "items";

    //This other variables will be for creating the value of the day
    public static final String VALUE_BASE_URL = "http://api.walmartlabs.com/v1/vod?";
    public static final String VALUE_FORMAT= "format";
    //This will be the one to take changes to our firebase once we drag an item to a different location
    public static final String FIREBASE_QUERY_INDEX = "index";
}
