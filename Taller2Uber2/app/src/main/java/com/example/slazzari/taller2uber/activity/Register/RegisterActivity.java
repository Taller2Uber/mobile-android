package com.example.slazzari.taller2uber.activity.Register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.BaseActivity;
import com.example.slazzari.taller2uber.activity.home.driver.DriverHomeActivity;
import com.example.slazzari.taller2uber.activity.home.passenger.PassengerHomeActivity;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.networking.NetworkingConstants;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.AUTHORIZATION_KEY;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Registracion");

        Gson gson = new Gson();
        String strUser = getIntent().getStringExtra("obj");
        user = gson.fromJson(strUser, User.class);



        Button registerDriverButton = (Button) findViewById(R.id.register_driver_button);
        registerDriverButton.setOnClickListener(this);

        Button registerPassengerButton = (Button) findViewById(R.id.register_passenger_button);
        registerPassengerButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.register_driver_button :
                Userinteractor.registerDriver(user).enqueue(new Callback<User>() {
                                                                   @Override
                                                                   public void onResponse(Call<User> call, Response<User> response) {
                                                                       User responseUser = response.body();
                                                                       authenticateUser(responseUser);
                                                                   }

                                                                   @Override
                                                                   public void onFailure(Call<User> call, Throwable t) {
                                                                       Toast.makeText(RegisterActivity.this, "No se pudo registrar el pasajero", Toast.LENGTH_LONG).show();
                                                                   }
                                                               }
                );

                break;
            case R.id.register_passenger_button :

                Userinteractor.registerPassenger(user).enqueue(new Callback<User>() {
                       @Override
                       public void onResponse(Call<User> call, Response<User> response) {
                           User responseUser = response.body();
                           authenticateUser(responseUser);
                       }

                       @Override
                       public void onFailure(Call<User> call, Throwable t) {
                           Toast.makeText(RegisterActivity.this, "No se pudo registrar el pasajero", Toast.LENGTH_LONG).show();
                       }
                    }
                );

                break;
        }
    }

    private void authenticateUser(User user) {
        Userinteractor.loginUser(user).enqueue(
                new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        User responseUser = response.body();

                        String authorization = response.headers().get(AUTHORIZATION_KEY);
                        NetworkingConstants.authToken = authorization;

                        Intent intent;
                        if (responseUser.isPassenger()) {
                            intent = new Intent(RegisterActivity.this, PassengerHomeActivity.class);
                        } else {
                            intent = new Intent(RegisterActivity.this, DriverHomeActivity.class);
                        }

                        Gson gson = new Gson();
                        intent.putExtra("obj", gson.toJson(responseUser));

                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                    }
                }
        );

    }
}
