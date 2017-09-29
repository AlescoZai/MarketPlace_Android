package com.example.dennis.marketplace.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dennis.marketplace.Constants;
import com.example.dennis.marketplace.R;
import com.example.dennis.marketplace.adapters.FirebaseItemViewHolder;
import com.example.dennis.marketplace.models.Market;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedItemListActivity extends AppCompatActivity {
    private DatabaseReference mMarketReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

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
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Market, FirebaseItemViewHolder>
                (Market.class, R.layout.item_list_item_drag, FirebaseItemViewHolder.class,
                        mMarketReference){

            @Override
            protected void populateViewHolder(FirebaseItemViewHolder viewHolder,
                                              Market model, int position) {
                viewHolder.bindItems(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }
    /*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
    */
}
