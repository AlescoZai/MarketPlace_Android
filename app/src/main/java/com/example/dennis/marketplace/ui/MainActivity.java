package com.example.dennis.marketplace.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dennis.marketplace.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //Lets declare some variables

    //This is butterknife
    @Bind(R.id.btn) Button myButton;
    @Bind(R.id.myname) EditText myEditText;
    @Bind(R.id.thisText) TextView myText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                Intent intent = new Intent(MainActivity.this, WhatToBuy.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}
