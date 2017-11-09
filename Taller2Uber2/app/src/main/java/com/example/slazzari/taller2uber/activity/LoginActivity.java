package com.example.slazzari.taller2uber.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Login");

        setContentView(R.layout.activity_login);

        LoginButton loginButton = (LoginButton) this.findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions("email");

        Button loginNativeButton = (Button) this.findViewById(R.id.login_button);
        loginNativeButton.setOnClickListener(this);

        Button registerButton = (Button) this.findViewById(R.id.register_button);
        registerButton.setOnClickListener(this);


        // Other app specific specialization

        // Callback registration
        callbackManager = CallbackManager.Factory.create();

        Toast.makeText(LoginActivity.this, "Login Button" +  loginButton, Toast.LENGTH_LONG).show();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
//                TODO:if (el token está registrado)
//                  TODO:entrar derecho a la home con ese usuario


                Userinteractor.loginUser(new FacebookToken(AccessToken.getCurrentAccessToken().getToken())).enqueue(
                        new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {

                                User responseUser = response.body();

                                if (responseUser == null) {
                                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                                    User user = new User();
                                    user.setFbToken(AccessToken.getCurrentAccessToken().getToken());
                                    Gson gson = new Gson();
                                    intent.putExtra("obj", gson.toJson(user));
                                    startActivity(intent);
                                } else {
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
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_button:
                Toast.makeText(LoginActivity.this, "Register", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                User user = new User();
                user.setFbToken(AccessToken.getCurrentAccessToken().getToken());
                Gson gson = new Gson();
                intent.putExtra("obj", gson.toJson(user));
                startActivity(intent);

                break;
            case R.id.login_button:
                Toast.makeText(LoginActivity.this, "login", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
