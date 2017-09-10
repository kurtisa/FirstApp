package com.example.kurtis.firstapp;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by sophr on 5/09/2017.
 */

class SetTasksAdapter extends RecyclerView.Adapter<SetTasksAdapter.ViewHolder> {
    private String[] mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public SetTasksAdapter(ArrayList hi, ArrayList paired) {
        Collections.sort(hi, String.CASE_INSENSITIVE_ORDER);
        mDataset = (String[]) hi.toArray(new String[0]);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SetTasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        LinearLayoutCompat v = (LinearLayoutCompat) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.set_tasks_listview, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final TextView mTextView = holder.mListItem.findViewById(R.id.set_task_student_username);
        mTextView.setText(mDataset[position]);

        holder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set your object's last status
                if (isChecked && !setTasksActivity.studentCheckedList.contains(mTextView.getText())) {
                    setTasksActivity.studentCheckedList.add((String) mTextView.getText());
                } else if (!isChecked && setTasksActivity.studentCheckedList.contains(mTextView.getText())) {
                    setTasksActivity.studentCheckedList.remove(mTextView.getText());
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayoutCompat mListItem;
        // each data item is just a string in this case
        CheckBox cbSelect;

        public ViewHolder(LinearLayoutCompat v) {
            super(v);
            mListItem = v;
            cbSelect = v.findViewById(R.id.setTasksCheckbox);

        }
    }
}
