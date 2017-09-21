package com.example.dennis.marketplace.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dennis.marketplace.Constants;
import com.example.dennis.marketplace.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //Now lets create a database reference
    private DatabaseReference mSearcheditemReference;
    //This is butterknife
    @Bind(R.id.btn) Button myButton;
    @Bind(R.id.myname) EditText myEditText;
    @Bind(R.id.thisText) TextView myText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Parse the searched item as an argument
        mSearcheditemReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_ITEM);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //IMplement butterknife
        ButterKnife.bind(this);

        //Lets add a new font to make our app awesome
        myText = (TextView) findViewById(R.id.thisText);
        Typeface smiling = Typeface.createFromAsset(getAssets(),"fonts/Laughing and Smiling.ttf");
        myText.setTypeface(smiling);

        //Set an onclick listener
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = myEditText.getText().toString();
                //This is supposed to save our stuff in the firebase
                saveCommoditiesToFirebase(name);
                Intent intent = new Intent(MainActivity.this, WhatToBuy.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
//This is supposed to set the value in our firebase
    public void saveCommoditiesToFirebase(String name) {
        mSearcheditemReference.setValue(name);
    }
}
