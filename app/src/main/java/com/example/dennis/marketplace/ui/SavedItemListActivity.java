package com.example.dennis.marketplace.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.dennis.marketplace.Constants;
import com.example.dennis.marketplace.R;
import com.example.dennis.marketplace.adapters.FirebaseItemListAdapter;
import com.example.dennis.marketplace.adapters.FirebaseItemViewHolder;
import com.example.dennis.marketplace.models.Market;
import com.example.dennis.marketplace.util.OnStartDragListener;
import com.example.dennis.marketplace.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedItemListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mMarketReference;
    //private FirebaseRecyclerAdapter mFirebaseAdapter;
    private FirebaseItemListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whattobuy_activity);

        ButterKnife.bind(this);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        mMarketReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_ITEM)
                .child(uid);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query= FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_ITEM)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);



        mFirebaseAdapter = new FirebaseItemListAdapter(Market.class, R.layout.item_list_item_drag, FirebaseItemViewHolder.class,
                query, this, this) {

            @Override
            protected void populateViewHolder(FirebaseItemViewHolder viewHolder,
                                              Market model, int position) {
                viewHolder.bindItems(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

}
