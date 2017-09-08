package com.example.dennis.marketplace;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by dennis on 9/8/17.
 */

public class HubBusinessAdapter extends ArrayAdapter {
    //INitialize the variables in the what to buys
    private Context mContext;
    private String[] BucketList;
    private String[] Whathaves;

    public HubBusinessAdapter(Context mContext, int resource, String[] BucketList, String[] Whathaves){
        super(mContext, resource);
        this.mContext = mContext;
        this.BucketList = BucketList;
        this.Whathaves = Whathaves;
    }


    @Override
    public Object getItem(int position) {
        String bucket = BucketList[position];
        String whathaves = Whathaves[position];
        return String.format("%s \nHas: %s", bucket, whathaves);
    }

    @Override
    public int getCount() {
        return BucketList.length;
    }
}
