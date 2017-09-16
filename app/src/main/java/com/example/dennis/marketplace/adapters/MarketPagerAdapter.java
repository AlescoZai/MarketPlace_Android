package com.example.dennis.marketplace.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dennis.marketplace.models.Market;
import com.example.dennis.marketplace.ui.MarketDetailFRagment;

import java.util.ArrayList;

/**
 * Created by dennis on 9/15/17.
 */

public class MarketPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Market> mMarket;

    public MarketPagerAdapter(FragmentManager fm, ArrayList<Market> markets) {
        super(fm);
        mMarket = markets;
    }

    @Override
    public Fragment getItem(int position) {
        return MarketDetailFRagment.newInstance(mMarket.get(position));
    }

    @Override
    public int getCount() {
        return mMarket.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMarket.get(position).getName();
    }
}
