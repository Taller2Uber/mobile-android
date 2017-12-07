package com.example.slazzari.taller2uber.activity.Register;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.home.driver.AvailableRoutesRecyclerViewAdapter;
import com.example.slazzari.taller2uber.model.Car;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by slazzari on 10/12/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Car> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView plateTextView;
        public TextView brandTextView;
        public TextView modelTextView;
        public TextView yearTextView;

        public ViewHolder(View v) {
            super(v);

            plateTextView = (TextView) v.findViewById(R.id.car_cell_licence_plate);
            brandTextView = (TextView) v.findViewById(R.id.car_cell_brand);
            modelTextView = (TextView) v.findViewById(R.id.car_cell_model);
            yearTextView = (TextView) v.findViewById(R.id.car_cell_year);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Car> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_driver_car,parent, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Car car = mDataset.get(position);

        holder.plateTextView.setText(car.getLicensePlate());
        holder.brandTextView.setText(car.getBrand());
        holder.modelTextView.setText(car.getModel());
        holder.yearTextView.setText(car.getYear());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
