package com.example.slazzari.taller2uber.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private CallbackManager callbackManager;

    private EditText usernameEditText;
    private EditText passwordEditText;

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

        usernameEditText = (EditText) this.findViewById(R.id.username_edit_text);
        passwordEditText = (EditText) this.findViewById(R.id.password_edit_text);

        // Other app specific specialization

        // Callback registration
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
//                TODO:if (el token está registrado)
//                  TODO:entrar derecho a la home con ese usuario

                User loginUser = new User();
                loginUser.setFbToken(AccessToken.getCurrentAccessToken().getToken());
                Userinteractor.loginUser(loginUser).enqueue(
                        new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {

                                String authorization = response.headers().get("authorization");

                                User responseUser = response.body();

                                if (responseUser == null) {
                                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                                    User user = new User();
                                    user.setFbToken(AccessToken.getCurrentAccessToken().getToken());
                                    String firebaseToken = FirebaseInstanceId.getInstance().getToken().toString();

                                    user.setFirebaseToken(firebaseToken);
                                    Gson gson = new Gson();
                                    intent.putExtra("obj", gson.toJson(user));
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Intent intent;
                                    if (responseUser.isPassenger()) {
                                        intent = new Intent(LoginActivity.this, PassengerHomeActivity.class);
                                    } else {
                                        intent = new Intent(LoginActivity.this, DriverHomeActivity.class);
                                    }

                                    Gson gson = new Gson();
                                    intent.putExtra("obj", gson.toJson(responseUser));

                                    startActivity(intent);
                                    finish();
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
        final User user = new User();

        user.setUserName(usernameEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());

//        String firebaseToken = FirebaseInstanceId.getInstance().getToken().toString();
//        user.setFirebaseToken(firebaseToken);


        switch (view.getId()) {
            case R.id.register_button:
                Toast.makeText(LoginActivity.this, "Register", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                Gson gson = new Gson();
                intent.putExtra("obj", gson.toJson(user));
                startActivity(intent);
                finish();

                break;
            case R.id.login_button:
                Userinteractor.loginUser(user).enqueue(
                        new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {

                                User responseUser = response.body();

                                if (responseUser == null) {
                                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                                    User user = new User();
                                    String firebaseToken = FirebaseInstanceId.getInstance().getToken().toString();

                                    user.setFirebaseToken(firebaseToken);
                                    Gson gson = new Gson();
                                    intent.putExtra("obj", gson.toJson(user));
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Intent intent;
                                    if (responseUser.isPassenger()) {
                                        intent = new Intent(LoginActivity.this, PassengerHomeActivity.class);
                                    } else {
                                        intent = new Intent(LoginActivity.this, DriverHomeActivity.class);
                                    }

                                    SharedPreferences preferences = getBaseContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();

                                    editor.putString("username",user.getUserName());
                                    editor.putString("password",user.getPassword());

                                    editor.commit();

                                    Gson gson = new Gson();
                                    intent.putExtra("obj", gson.toJson(responseUser));

                                    startActivity(intent);
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                            }
                        }
                );
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
