package com.example.dennis.marketplace.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dennis.marketplace.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = CreateAccountActivity.class.getSimpleName();
    //Now we bind
    @Bind(R.id.editEmail)EditText myEmail;
    @Bind(R.id.editPassword)EditText thisPassword;
    @Bind(R.id.createUser)TextView createUser;
    @Bind(R.id.logIn)Button login;
//Initialize the fire base authentication
    private FirebaseAuth mAuth;
//THis will be a progress dialog
    private ProgressDialog mAuthProgressDialog;

    //We add the authstate listener
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //Then we butterknife these thing
        ButterKnife.bind(this);
        //Now here get the instance of firebase
        mAuth = FirebaseAuth.getInstance();

        //Set the onClick on the create user text view

        createUser.setOnClickListener(this);
        login.setOnClickListener(this);

        //This will generate an intent that will transfer us from the login to the createuser
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LogIn.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

        //Initialize the prograss bar

        createAuthProgressDialog();

    }

    //This will generate an intent that will transfer us from the login to the createuser
    @Override
    public void onClick(View view){
        if (view == createUser){
            Intent intent = new Intent(LogIn.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        }
        if (view == login){
            loginActivity();
        }
    }

    public void loginActivity(){
        String email = myEmail.getText().toString().trim();
        String myPassword = thisPassword.getText().toString().trim();

        //Check the credentials
        if (email.equals("")){
            myEmail.setText("Please enter the email");
            return;
        }
        if(myPassword.equals("")){
            thisPassword.setText("Please enter the password");
            return;
        }
        mAuthProgressDialog.show();
//THis is the built in method for fire base
        mAuth.signInWithEmailAndPassword(email, myPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(LogIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Logging you in");
        mAuthProgressDialog.setCancelable(false);
    }
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
