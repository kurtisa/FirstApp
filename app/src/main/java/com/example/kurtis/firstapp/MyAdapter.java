package com.example.kurtis.firstapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by sophr on 5/09/2017.
 */

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mDataset;
    private Boolean[] mPaired;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList hi, ArrayList paired) {
        Collections.sort(hi, String.CASE_INSENSITIVE_ORDER);

        mDataset = (String[]) hi.toArray(new String[0]);
        mPaired = (Boolean[]) paired.toArray(new Boolean[0]);

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_students_listview, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        TextView mTextView = holder.mCardView.findViewById(R.id.student_username);
        ImageView mImageView = holder.mCardView.findViewById(R.id.paired_image);


        mTextView.setText(mDataset[position]);

        if (mPaired[position]) {
            mImageView.setImageResource(R.drawable.ic_stat_name);
        } else {
            mImageView.setImageResource(R.drawable.ic_not_paired);

        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        Log.d("WHAT YOU THINK LENGTH", Integer.toString(mDataset.length));
        return mDataset.length;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public CardView mCardView;

        public ViewHolder(CardView v) {
            super(v);
            mCardView = v;
        }
    }
}
