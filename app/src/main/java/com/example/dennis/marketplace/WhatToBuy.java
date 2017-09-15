package com.example.dennis.marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;



/**
 * Created by dennis on 9/8/17.
 */

public class WhatToBuy extends AppCompatActivity{
    public static final String TAG = WhatToBuy.class.getSimpleName();
//Lets Create some butterknife
    @Bind(R.id.myText) TextView thisText;
    @Bind(R.id.thisList) ListView myList;

    //Arraylist for the items
    public ArrayList<Market> mMarket = new ArrayList<>();

    //Lets declare the arrays for the commodies
    /*
    private String[] BucketList = new String[] {"Bread", "Tusker",
            "Guiness", "Cakes", "Delamere", "BasketBall",
            "Trousers", "Jackets", "Toothpaste", "Laptops",
            "Mousse", "FUll Grid", "Glasses",
            "Muturas", "Subway footlongs", "Muturas", "Subway footlongs"};
    //This is another array where we will create a custom adpter that will sync together with the array above
    private String[] Whathaves = new String[] {"40 clas", "4% alcoholic percentage", "6% alcoholic percentage",
            "Sweet",
            "NIcest yoghurt ever tasted", "NIce ball", "size 36", "Large",
            "Colgate", "Apple", "Azela", "Cuban", "Gucci", "nice", "Breakfast"};
*/
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whattobuy_activity);

        //Initialize the butterknife
        ButterKnife.bind(this);

        //Here is where the text is gotten from the intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        thisText.setText("Hello" + name);
        getItems(name);
    }
    //Now this method is for getting the items and showing if there is anything passed
    public void getItems(String item){
        final MarketService marketService = new MarketService();
        MarketService.findItem(item, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //String jsonData = response.body().string();
                mMarket = marketService.processResults(response);

                //Now lets Define a thread
                WhatToBuy.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] itemNames = new String[mMarket.size()];

                        //Initialize For loop
                        for (int i =0; i < itemNames.length; i++){
                            itemNames[i] = mMarket.get(i).getName();
                        }
                        //New array adapter
                        ArrayAdapter adapter = new ArrayAdapter(WhatToBuy.this,
                                android.R.layout.simple_list_item_1, itemNames);
                        myList.setAdapter(adapter);
                        //This is
                        for (Market restaurant : mMarket) {
                            Log.d(TAG, "name: " + restaurant.getName());
                            Log.d(TAG, "salePrice: " + restaurant.getSalePrice());
                            Log.d(TAG, "image: " + restaurant.getImage());
                            Log.d(TAG, "stock: " + restaurant.getStock());

                        }
                    }
                });
            }
        });

    }
}
