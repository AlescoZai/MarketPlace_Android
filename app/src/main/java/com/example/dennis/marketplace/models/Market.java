package com.example.dennis.marketplace.models;

/**
 * Created by dennis on 9/15/17.
 */

public class Market {
    //Pass the parameters that we will use
    private String mImage;
    private String mSaleprice;
    private String mName;
    private String mStock;
    //Now lets pass a Constructor

    public Market(String image, String saleprice, String name, String stock){
        this.mImage = image;
        this.mSaleprice = saleprice;
        this.mName = name;
        this.mStock = stock;
    }
    public String getImage(){
        return mImage;
    }
    public String getSalePrice(){
        return mSaleprice;
    }
    public String getName(){
        return mName;
    }
    public String getStock(){
        return mStock;
    }
}
