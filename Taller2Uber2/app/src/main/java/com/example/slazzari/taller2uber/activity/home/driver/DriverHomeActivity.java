package com.example.slazzari.taller2uber.activity.home.driver;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.chat.ChatActivity;
import com.example.slazzari.taller2uber.activity.home.passenger.MapsActivity;
import com.example.slazzari.taller2uber.model.TrackingLooper;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.model.map.AvailableRoute;
import com.example.slazzari.taller2uber.networking.interactor.Routesinteractor;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverHomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button editDriverButton;
    private Button chatButton;
    private Button beginDriveButton;
    private Button finishDriveButton;

    private User user;
    private RecyclerView availableRoutesRecyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);

        Gson gson = new Gson();
        String strUser = getIntent().getStringExtra("obj");
        user = gson.fromJson(strUser, User.class);


        editDriverButton = (Button) findViewById(R.id.driver_home_edit_passenger_button);
        editDriverButton.setOnClickListener(this);

        chatButton = (Button) findViewById(R.id.driver_chat_button);
        chatButton.setOnClickListener(this);

        beginDriveButton = (Button) findViewById(R.id.driver_home_begin_drive_button);
        beginDriveButton.setOnClickListener(this);

        finishDriveButton = (Button) findViewById(R.id.driver_home_finish_drive_button);
        finishDriveButton.setOnClickListener(this);

        availableRoutesRecyclerView = (RecyclerView) findViewById(R.id.available_routes_recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.available_routes_recycler_view_swipe_to_refresh);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(availableRoutesRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        availableRoutesRecyclerView.addItemDecoration(dividerItemDecoration);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAvailableRoutes();
            }
        });

        TrackingLooper.getInstance().setup(this);

        getAvailableRoutes();
    }

    private void getAvailableRoutes() {
        Routesinteractor.getAvailableRoutes().enqueue(new Callback<List<AvailableRoute>>() {
            @Override
            public void onResponse(Call<List<AvailableRoute>> call, Response<List<AvailableRoute>> response) {
                List<AvailableRoute> availableRoutes = response.body();

                swipeRefreshLayout.setRefreshing(false);

                AvailableRoutesRecyclerViewAdapter routesAdapter = new AvailableRoutesRecyclerViewAdapter(availableRoutes, new AvailableViewOnClick() {
                    @Override
                    public void onClickRoute(AvailableRoute availableRoute) {
                        Intent driverViewRouteIntent = new Intent(DriverHomeActivity.this, DriverViewRouteActivity.class);
                        Gson gson = new Gson();

                        String jsonRoute = gson.toJson(availableRoute.getRoute());
                        driverViewRouteIntent.putExtra("obj", jsonRoute);

                        startActivity(driverViewRouteIntent);
                    }

                    @Override
                    public void onClickAcceptRoute(AvailableRoute availableRoute) {
                        availableRoute.setDriverId(Integer.parseInt(user.getSsId()));
                        Routesinteractor.driverConfritmRoute(availableRoute).enqueue(
                                new Callback<AvailableRoute>() {
                                    @Override
                                    public void onResponse(Call<AvailableRoute> call, Response<AvailableRoute> response) {
                                        AvailableRoute responseRoute = response.body();
                                    }

                                    @Override
                                    public void onFailure(Call<AvailableRoute> call, Throwable t) {
                                    }
                                }
                        );
                    }
                });

                availableRoutesRecyclerView.setAdapter(routesAdapter);
                LinearLayoutManager llm = new LinearLayoutManager(DriverHomeActivity.this);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                availableRoutesRecyclerView.setLayoutManager(llm);
            }

            @Override
            public void onFailure(Call<List<AvailableRoute>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(View view) {

        Gson gson = new Gson();

        switch (view.getId()) {
            case R.id.driver_home_edit_passenger_button:
                Intent driverActivityIntent = new Intent(DriverHomeActivity.this, DriverDescriptionActivity.class);

                String stringUser = gson.toJson(user);
                driverActivityIntent.putExtra("obj", stringUser);

                startActivity(driverActivityIntent);

                break;
            case R.id.driver_chat_button:
                Intent chatActivityIntent = new Intent(DriverHomeActivity.this, ChatActivity.class);

                startActivity(chatActivityIntent);
                break;

            case R.id.driver_home_begin_drive_button:


                break;
            case R.id.driver_home_finish_drive_button:
                break;
        }
    }
}