package com.example.dennis.marketplace.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dennis.marketplace.R;
import com.example.dennis.marketplace.models.Market;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MarketDetailFRagment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    @Bind(R.id.ImageView) ImageView mImageLabel;
    @Bind(R.id.NameTextView) TextView mNameLabel;
    @Bind(R.id.PriceTextView) TextView mPrice;
    @Bind(R.id.StockTextView) TextView mStockLabel;


    private Market mMarket;


    public static MarketDetailFRagment newInstance(Market market) {
        MarketDetailFRagment marketDetailFragment = new MarketDetailFRagment();
        Bundle args = new Bundle();
        args.putParcelable("market", Parcels.wrap(market));
        marketDetailFragment.setArguments(args);
        return marketDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMarket = Parcels.unwrap(getArguments().getParcelable("market"));
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_market_detail_fragment, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mMarket.getImage()).into(mImageLabel);

        mNameLabel.setText(mMarket.getName());
        mPrice.setText(mMarket.getSalePrice());
        mStockLabel.setText(mMarket.getStock());

        return view;
    }

}
