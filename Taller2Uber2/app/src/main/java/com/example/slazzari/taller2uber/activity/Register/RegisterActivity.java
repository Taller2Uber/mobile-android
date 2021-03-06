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
import com.example.slazzari.taller2uber.model.login.LoginManager;
import com.example.slazzari.taller2uber.model.login.LoginManagerResponse;
import com.example.slazzari.taller2uber.networking.NetworkingConstants;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.google.firebase.iid.FirebaseInstanceId;
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
        String firebaseToken = FirebaseInstanceId.getInstance().getToken();
        user.setFirebaseToken(firebaseToken);

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

    private void authenticateUser(User authUser) {
        authUser.setFbToken(user.getFbToken());
        authUser.setPassword(user.getPassword());
        authUser.setUserName(user.getUserName());

        new LoginManager().login(authUser, new LoginManagerResponse() {
            @Override
            public void onLoginWithUser(User user) {
                Intent intent;
                if (user.isPassenger()) {
                    intent = new Intent(RegisterActivity.this, PassengerHomeActivity.class);
                } else {
                    intent = new Intent(RegisterActivity.this, DriverHomeActivity.class);
                }

                Gson gson = new Gson();
                intent.putExtra("obj", gson.toJson(user));

                startActivity(intent);
                finish();
            }

            @Override
            public void onLoginWithFailure() {
                Toast.makeText(RegisterActivity.this, "Se registró el pasajero pero no se pudo entrar a la aplicacion", Toast.LENGTH_LONG).show();
            }
        });
    }
}
