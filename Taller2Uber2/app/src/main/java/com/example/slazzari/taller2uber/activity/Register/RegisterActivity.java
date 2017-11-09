package com.example.slazzari.taller2uber.activity.Register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.BaseActivity;
import com.example.slazzari.taller2uber.activity.home.PassengerHomeActivity;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        // TODO Auto-generated method stub

        Gson gson = new Gson();

        switch(v.getId())
        {
            case R.id.register_driver_button :
                Toast.makeText(RegisterActivity.this,"Driver button was tapped", 1000).show();
                Intent driverActiviryIntent = new Intent(RegisterActivity.this, RegisterDriverActivity.class);

                driverActiviryIntent.putExtra("obj", gson.toJson(user));

                startActivity(driverActiviryIntent);

                break;
            case R.id.register_passenger_button :

                Userinteractor.registerPassenger(user).enqueue(new Callback<User>() {
                       @Override
                       public void onResponse(Call<User> call, Response<User> response) {
                           User responseUser = response.body();
                           Intent passengerActivityIntent = new Intent(RegisterActivity.this, PassengerHomeActivity.class);
                           Gson gson = new Gson();
                           passengerActivityIntent.putExtra("obj", gson.toJson(responseUser));

                           startActivity(passengerActivityIntent);
                       }

                       @Override
                       public void onFailure(Call<User> call, Throwable t) {
                           Toast.makeText(RegisterActivity.this, "No se pudo registrar el pasajero", Toast.LENGTH_LONG).show();
                       }
                    }
                );


//                Toast.makeText(RegisterActivity.this,"Passenger button was tapped", 1000).show();
//                Intent passengerActivityIntent = new Intent(RegisterActivity.this, RegisterPassengerActivity.class);
//
//                passengerActivityIntent.putExtra("obj", gson.toJson(user));
//
//                startActivity(passengerActivityIntent);
                break;
        }
    }
}
