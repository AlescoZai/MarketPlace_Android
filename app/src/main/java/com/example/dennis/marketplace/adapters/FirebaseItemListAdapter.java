package com.example.dennis.marketplace.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.example.dennis.marketplace.models.Market;
import com.example.dennis.marketplace.ui.MarketDetailActivity;
import com.example.dennis.marketplace.util.ItemTouchHelperAdapter;
import com.example.dennis.marketplace.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dennis on 9/29/17.
 */

public class FirebaseItemListAdapter extends FirebaseRecyclerAdapter<Market, FirebaseItemViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
//The childeventlistener is supposed to grab our items and store them in the arraylist
    private ChildEventListener mChildEventListener;
    private ArrayList<Market> mMarket = new ArrayList<>();


    //Create a constructor for this class
    public FirebaseItemListAdapter(Class<Market> modelClass, int modelLayout,
                                         Class<FirebaseItemViewHolder> viewHolderClass,
                                         Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mMarket.add(dataSnapshot.getValue(Market.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
//This stores  our X and Y coordinates on the position of our drag
    //Notifies what will happen to the item once it is moved in the firebase
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //Notify that the data adapter has changed
        //These reflect changes in the fire base
        Collections.swap(mMarket, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        //These reflect changes in the fire base
        mMarket.remove(position);
        getRef(position).removeValue();
    }
//This will be the one to populate our view holder
    @Override
    protected void populateViewHolder(final FirebaseItemViewHolder viewHolder, Market model, int position) {
        viewHolder.bindItems(model);
        viewHolder.mItemImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MarketDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("restaurants", Parcels.wrap(mMarket));
                mContext.startActivity(intent);
            }
        });
    }

    private void setIndexInFirebase() {
        for (Market market : mMarket) {
            int index = mMarket.indexOf(market);
            DatabaseReference ref = getRef(index);
            market.setIndex(Integer.toString(index));
            ref.setValue(market);
        }
    }
    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}
