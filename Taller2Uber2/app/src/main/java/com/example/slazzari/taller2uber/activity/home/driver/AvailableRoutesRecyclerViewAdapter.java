package com.example.slazzari.taller2uber.activity.home.driver;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.model.map.AvailableRoute;

import java.util.List;

/**
 * Created by slazzari on 11/28/17.
 */

interface AvailableViewOnClick {
    void onClickRoute(AvailableRoute availableRoute);
    void onClickAcceptRoute(AvailableRoute availableRoute);
}


public class AvailableRoutesRecyclerViewAdapter extends
        RecyclerView.Adapter<AvailableRoutesRecyclerViewAdapter.MyViewHolder> {

    private List<AvailableRoute> availableRoutes;
    private AvailableViewOnClick onClickListener;

    public AvailableRoutesRecyclerViewAdapter(List<AvailableRoute> availableRoutes, AvailableViewOnClick onClickListener) {
        this.availableRoutes = availableRoutes;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_available_route,parent, false);
        return new MyViewHolder(v, onClickListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AvailableRoute route = availableRoutes.get(position);
        holder.availableRoute = route;
    }

    @Override
    public int getItemCount() {
        return availableRoutes.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Button seeButton;
        public Button acceptButton;
        public AvailableRoute availableRoute;
        public AvailableViewOnClick onClickListener;

        public MyViewHolder(View view, AvailableViewOnClick onClickListener) {
            super(view);
            seeButton = (Button) view.findViewById(R.id.see_route_button);
            seeButton.setOnClickListener(this);

            acceptButton = (Button) view.findViewById(R.id.start_route_button);
            acceptButton.setOnClickListener(this);


            this.onClickListener = onClickListener;
        }

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.see_route_button:
                    onClickListener.onClickRoute(availableRoute);
                    break;
                case R.id.start_route_button:
                    onClickListener.onClickAcceptRoute(availableRoute);
            }
        }
    }
}
