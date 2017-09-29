package com.example.dennis.marketplace.util;

/**
 * Created by dennis on 9/29/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
