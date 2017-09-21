package com.example.dennis.marketplace.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.dennis.marketplace.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    //Now we bind
    @Bind(R.id.createUser)TextView createUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //Then we butterknife these thing
        ButterKnife.bind(this);

        //Set the onClick on the create user text vioew
    }
}
