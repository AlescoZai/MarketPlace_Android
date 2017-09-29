package com.example.dennis.marketplace.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.example.dennis.marketplace.models.Market;
import com.example.dennis.marketplace.util.ItemTouchHelperAdapter;
import com.example.dennis.marketplace.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by dennis on 9/29/17.
 */

public class FirebaseItemListAdapter extends FirebaseRecyclerAdapter<Market, FirebaseItemViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

//Create a constructor for this class
    public FirebaseItemListAdapter(Class<Market> modelClass, int modelLayout,
                                         Class<FirebaseItemViewHolder> viewHolderClass,
                                         Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }
//This stores  our X and Y coordinates on the position of our drag
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

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
    }
}
