package com.example.slazzari.taller2uber.activity;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.slazzari.taller2uber.activity.Register.MyAdapter;
import com.example.slazzari.taller2uber.model.Car;
import com.example.slazzari.taller2uber.model.Payment.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slazzari on 11/30/17.
 */

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.ViewHolder> {
    private List<String> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public EditText editText;
        public ViewHolder(EditText v) {
            super(v);
            editText = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PayAdapter(List<String> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        // create a new view
        EditText v = new EditText(parent.getContext());

        v.setTextSize(20);
        PayAdapter.ViewHolder vh = new PayAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PayAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.editText.setHint(mDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
