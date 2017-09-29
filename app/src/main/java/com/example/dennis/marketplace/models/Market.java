package com.example.dennis.marketplace.models;

import org.parceler.Parcel;

/**
 * Created by dennis on 9/15/17.
 */
@Parcel
public class Market {
    //Pass the parameters that we will use
    private String image;
    private String saleprice;
    private String name;
    private String stock;
    private String pushId;
    String index;
    /*
    private String mUrl;
    private String mAddToCart;
    */
    //Now lets pass a Constructor
    public Market() {}

    public Market(String image, String saleprice, String name, String stock){
        this.image = image;
        this.saleprice = saleprice;
        this.name = name;
        this.stock = stock;
        /*
        this.mUrl = url;
        this.mAddToCart = addToCart;
        */
    }
    public String getImage(){
        return image;
    }
    public String getSalePrice(){
        return saleprice;
    }
    public String getName(){
        return name;
    }
    public String getStock(){
        return stock;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
    public String getIndex(){ return index;}
    public void setIndex(String index){
        this.index = index;
    }
    /*
    public String getUrl(){ return mUrl;}
    public  String getmAddToCart(){ return mAddToCart;}

*/}
