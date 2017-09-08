package com.example.dennis.marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Specific extends AppCompatActivity {
    //Initialize the butterknife
    @Bind(R.id.specific_bucket)
    TextView bucket_specific;
//THis is where the app runs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific);
        ButterKnife.bind(this);
//Getting the intent from the what to buy class
        Intent intent = getIntent();
        String bucket = intent.getStringExtra("bucket");
        bucket_specific.setText(bucket);
    }
}
