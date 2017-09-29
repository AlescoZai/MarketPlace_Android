package com.example.dennis.marketplace.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dennis.marketplace.R;
import com.example.dennis.marketplace.models.Market;
import com.squareup.picasso.Picasso;

/**
 * Created by dennis on 9/22/17.
 */
/*
*Here we are goingf to attach the onDRag listener to our project
* This will be simply be used to drag our things
 */

public class FirebaseItemViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    public ImageView mItemImageView;

    View mView;
    Context mContext;

    public FirebaseItemViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        //itemView.setOnClickListener(this);
    }

    //Lets bind the items
    public void bindItems(Market market){
        mItemImageView = mView.findViewById(R.id.itemImageView);
        ImageView itemImageView = mView.findViewById(R.id.itemImageView);
        TextView nameTextView = mView.findViewById(R.id.itemNameTextView);
        TextView priceTextView = mView.findViewById(R.id.priceTextView);

        Picasso.with(mContext)
                .load(market.getImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mItemImageView);

        nameTextView.setText(market.getName());
        priceTextView.setText(market.getSalePrice());
    }
/*
    @Override
    public void onClick(View view) {
        final ArrayList<Market> items = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ITEM);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    items.add(snapshot.getValue(Market.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, MarketDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("restaurants", Parcels.wrap(items));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    */
}
