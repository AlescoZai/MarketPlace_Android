package com.example.dennis.marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
    //Lets declare the arrays for the commodies
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

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whattobuy_activity);

        //Initialize the butterknife
        ButterKnife.bind(this);

        //Implement Array adapter
        HubBusinessAdapter adapter = new HubBusinessAdapter(this, android.R.layout.simple_list_item_1, BucketList, Whathaves);
        myList.setAdapter(adapter);

      myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              String bucket = ((TextView)view).getText().toString();
              Intent intent = new Intent(WhatToBuy.this, Specific.class);
              intent.putExtra("bucket", bucket);
              startActivity(intent);
          }
      });
        //Here is where the text is gotten from the intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        thisText.setText("Hello" + name);
    }
}
