package com.example.slazzari.taller2uber.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.home.driver.DriverViewRouteActivity;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.model.map.AvailableRoute;
import com.example.slazzari.taller2uber.networking.interactor.Routesinteractor;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmActivity extends AppCompatActivity implements View.OnClickListener {

    private Button acceptButton;
    private Button cancelButton;
    private Button viewRouteButton;
    private Button viewDriverButton;

    private String routeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Gson gson = new Gson();
        routeId = getIntent().getStringExtra("obj");

        acceptButton = (Button) findViewById(R.id.confirm_accept_button);
        acceptButton.setOnClickListener(this);

        cancelButton = (Button) findViewById(R.id.confirm_cancel_button);
        cancelButton.setOnClickListener(this);

        viewRouteButton = (Button) findViewById(R.id.confirm_view_route_button);
        viewRouteButton.setOnClickListener(this);

        viewDriverButton = (Button) findViewById(R.id.confirm_view_driver_button);
        viewDriverButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        boolean didAccept = true;

        switch (view.getId()) {
            case R.id.confirm_accept_button:
                didAccept = true;

                Routesinteractor.passengerAcceptRoute(routeId, didAccept).enqueue(
                        new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        }
                );

                break;
            case R.id.confirm_cancel_button:
                didAccept = false;

                Routesinteractor.passengerAcceptRoute(routeId, didAccept).enqueue(
                        new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        }
                );

                break;
            case R.id.confirm_view_route_button:
                Routesinteractor.getRoute(routeId).enqueue(
                        new Callback<AvailableRoute>() {
                            @Override
                            public void onResponse(Call<AvailableRoute> call, Response<AvailableRoute> response) {
                                Intent intent = new Intent (ConfirmActivity.this, DriverViewRouteActivity.class);

                                AvailableRoute availableRoute = response.body();

                                Gson gson = new Gson();
                                intent.putExtra("obj", gson.toJson(availableRoute.getRoute()));
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<AvailableRoute> call, Throwable t) {

                            }
                        }
                );

        }

    }
}
