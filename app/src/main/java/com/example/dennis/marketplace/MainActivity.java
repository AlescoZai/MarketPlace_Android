package com.example.dennis.marketplace;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {
    //Lets declare some variables
    public TextView myText;

    //This is butterknife
@Bind(R.id.btn)
    Button myButton;
    @Bind(R.id.myText)
    EditText myEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lets add a new font to make our app awesome
        myText = (TextView) findViewById(R.id.thisText);
        Typeface smiling = Typeface.DEFAULT.createFromAsset(getAssets(),"fonts/Laughing and Smiling.ttf");
        myText.setTypeface(smiling);
    }
}
