package com.example.dennis.marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dennis on 9/8/17.
 */

public class WhatToBuy extends AppCompatActivity{
    public static final String TAG = WhatToBuy.class.getSimpleName();
//Lets Create some butterknife
    @Bind(R.id.myText) TextView thisText;
    @Bind(R.id.thisList) ListView myList;
    private String[] BucketList = new String[] {"Bread", "Tusker",
            "Guiness", "Cakes", "Delamere", "BasketBall",
            "Trousers", "Jackets", "Toothpaste", "Laptops",
            "Mousse", "FUll Grid", "Glasses",
            "Muturas", "Subway footlongs"};


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whattobuy_activity);

        //Initialize the butterknife
        ButterKnife.bind(this);

        //IMplement Array adapter
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, BucketList);
        myList.setAdapter(adapter);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        thisText.setText(name);
    }
}
