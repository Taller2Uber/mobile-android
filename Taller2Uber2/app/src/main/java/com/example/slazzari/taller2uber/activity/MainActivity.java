package com.example.slazzari.taller2uber.activity;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.Register.RegisterActivity;
import com.example.slazzari.taller2uber.activity.home.driver.DriverHomeActivity;
import com.example.slazzari.taller2uber.activity.home.passenger.PassengerHomeActivity;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.networking.NetworkingConstants;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.AUTHORIZATION_KEY;

public class MainActivity extends BaseActivity {
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getBaseContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);

        final String userName = preferences.getString("username",null);
        final String password = preferences.getString("password",null);

//      Si guarde en las sharedprefs el username y pass
        if ((userName != null) && (password != null)) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);

            Userinteractor.loginUser(user).enqueue(
                    new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                            User responseUser = response.body();

                            String authorization = response.headers().get(AUTHORIZATION_KEY);
                            NetworkingConstants.authToken = authorization;

                            if (responseUser == null) {
                                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);


                                String firebaseToken = FirebaseInstanceId.getInstance().getToken().toString();
                                User user = new User();
                                user.setUserName(userName);
                                user.setPassword(password);
                                user.setFirebaseToken(firebaseToken);
                                Gson gson = new Gson();
                                intent.putExtra("obj", gson.toJson(user));
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent;
                                if (responseUser.isPassenger()) {
                                    intent = new Intent(MainActivity.this, PassengerHomeActivity.class);
                                } else {
                                    intent = new Intent(MainActivity.this, DriverHomeActivity.class);
                                }

                                String firebaseToken = FirebaseInstanceId.getInstance().getToken().toString();

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

        }else{
//      Si no hay ningun usuario y contraseña en la app
//        Si no tengo el token debo ir a la pantalla de login o sign up
            if (AccessToken.getCurrentAccessToken() == null) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

                finish();
//        Tengo access token asi que debo loguearme e ir a la home
            } else {
//            Me falta el caso del login nativo
                User loginUser = new User();
                loginUser.setFbToken(AccessToken.getCurrentAccessToken().getToken());
                Userinteractor.loginUser(loginUser).enqueue(
                        new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                User responseUser = response.body();
                                String authorization = response.headers().get(AUTHORIZATION_KEY);
                                NetworkingConstants.authToken = authorization;


                                if (responseUser == null) {
                                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);

                                    User user = new User();
                                    user.setFbToken(AccessToken.getCurrentAccessToken().getToken());
                                    Gson gson = new Gson();
                                    intent.putExtra("obj", gson.toJson(user));
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Intent intent;

                                    if (responseUser.isPassenger()) {
                                        intent = new Intent(MainActivity.this, PassengerHomeActivity.class);
                                    } else {
                                        intent = new Intent(MainActivity.this, DriverHomeActivity.class);
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

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
