package com.example.dennis.marketplace.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.dennis.marketplace.R;
import com.example.dennis.marketplace.adapters.MarketPagerAdapter;
import com.example.dennis.marketplace.models.Market;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MarketDetailActivity extends AppCompatActivity {
    //Lets implement some binds on the view placeholder
    @Bind(R.id.viewPager) ViewPager mViewPager;

    //Also declare a variable in the which is an ArrayList the market
    private MarketPagerAdapter adapterViewPager;
    ArrayList<Market> mMarket = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_detail);

        //Now lets bind all the declared variables above
        ButterKnife.bind(this);

        //now lets unwrap the data in the parcels
        mMarket = Parcels.unwrap(getIntent().getParcelableExtra("markets"));

        //Now lets declare the starting position of the data
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new MarketPagerAdapter(getSupportFragmentManager(), mMarket);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
