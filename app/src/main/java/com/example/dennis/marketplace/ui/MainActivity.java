package com.example.dennis.marketplace.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dennis.marketplace.Constants;
import com.example.dennis.marketplace.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //Now lets create a database reference
    private DatabaseReference mSearcheditemReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    //If the user exits the place he or she should do that with this
    private ValueEventListener mSearchedItemReferenceListener;
    //This is butterknife
    @Bind(R.id.btn) Button myButton;
    @Bind(R.id.myname) EditText myEditText;
    @Bind(R.id.saved)Button saveButton;
    @Bind(R.id.logout)Button logOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Parse the searched item as an argument
        mSearcheditemReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_ITEM);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Implement butterknife
        ButterKnife.bind(this);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //display welcome message
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }
            }
        };

        //Now lets add the search value event listener
        mSearcheditemReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {//When something changes
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    String item = locationSnapshot.getValue().toString();
                    Log.d("Items  updated", "item: " + item); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Lets add a new font to make our app awesome
        /*
        myText = (TextView) findViewById(R.id.thisText);
        Typeface smiling = Typeface.createFromAsset(getAssets(),"fonts/Laughing and Smiling.ttf");
        myText.setTypeface(smiling);
*/
        //Set an onclick listener
        myButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        logOut.setOnClickListener(this);
    }

//This is supposed to set the value in our firebase
    public void saveCommoditiesToFirebase(String name) {
        mSearcheditemReference.push().setValue(name);
    }


    @Override
    public void onClick(View view) {
        if (view == myButton){
            String name = myEditText.getText().toString();
            //This is supposed to save our stuff in the firebase
            saveCommoditiesToFirebase(name);
            Intent intent = new Intent(MainActivity.this, WhatToBuy.class);
            intent.putExtra("name", name);
            startActivity(intent);
        }
        if (view == saveButton){
            Intent intent = new Intent(MainActivity.this, SavedItemListActivity.class);
            startActivity(intent);
        }
        if(view == logOut){
            logout();
        }

    }
    public void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LogIn.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

/*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearcheditemReference.removeEventListener(mSearchedItemReferenceListener);
    }
*/
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
