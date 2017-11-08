package com.example.slazzari.taller2uber.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.Register.RegisterActivity;
import com.example.slazzari.taller2uber.activity.home.DriverHomeActivity;
import com.example.slazzari.taller2uber.activity.home.PassengerHomeActivity;
import com.example.slazzari.taller2uber.model.FacebookToken;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Login");

        setContentView(R.layout.activity_login);


        LoginButton loginButton = (LoginButton) this.findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions("email");
        // Other app specific specialization

        // Callback registration
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
//                TODO:if (el token está registrado)
//                  TODO:entrar derecho a la home con ese usuario

                Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_LONG).show();

                Userinteractor.loginUser(new FacebookToken(AccessToken.getCurrentAccessToken().getToken())).enqueue(
                        new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {

                                User responseUser = response.body();

                                Intent intent;
                                if (responseUser.getCard() == null) {
                                    intent = new Intent(LoginActivity.this, PassengerHomeActivity.class);
                                } else {
                                    intent = new Intent(LoginActivity.this, DriverHomeActivity.class);
                                }

                                Gson gson = new Gson();
                                intent.putExtra("obj", gson.toJson(responseUser));


                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast.makeText(LoginActivity.this, "Login failure", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                                User user = new User();
                                user.setFbToken("EAALQMNov0CkBAA9Wzw9bj3CvjstiKl5gO191HUZCA8xmZBU3ZBfD58JJmolio8wzYG7OBVRFFK46d7uXDU9eUl5VaITtA6Qc0mI7JRliSyAapa9M6wyvK2ZCNasYTyamOlswzMfe0wm2ZCF9VCLOSLpIhrEYzTHBOgSZAZBZBbZAYCM2NZCpAZAh1mQT1ZCkIZBz4CAWTx9TwAfyfUpkz1c3xGmpYdDJWp75sRZB6jQOZC0ZBrpVqiNZCMZA7PYXZB6");
                                Gson gson = new Gson();
                                intent.putExtra("obj", gson.toJson(user));
                                startActivity(intent);
                            }
                        }
                );

            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login cancel", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_LONG).show();
            }
        });

    }
}
