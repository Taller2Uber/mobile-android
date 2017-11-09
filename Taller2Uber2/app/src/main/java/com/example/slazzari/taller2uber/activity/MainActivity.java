package com.example.slazzari.taller2uber.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.Register.RegisterActivity;
import com.example.slazzari.taller2uber.activity.Register.RegisterPassengerActivity;
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
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = new User();
        user.setName("Santiago Lazzari");
        user.setFbToken(AccessToken.getCurrentAccessToken().getToken());

        Gson gson = new Gson();
        Intent intentT = new Intent(MainActivity.this, PassengerHomeActivity.class);
        intentT.putExtra("obj", gson.toJson(user));
        startActivity(intentT);
//        finish();


//        Si no tengo el token debo ir a la pantalla de login o sign up
//        if (AccessToken.getCurrentAccessToken() == null) {
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//
////        Tengo access token asi que debo loguearme e ir a la home
//        } else {
//            Userinteractor.loginUser(new FacebookToken(AccessToken.getCurrentAccessToken().getToken())).enqueue(
//                    new Callback<User>() {
//                        @Override
//                        public void onResponse(Call<User> call, Response<User> response) {
//
//                            User responseUser = response.body();
//
//                            if (responseUser == null) {
//                                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//
//                                User user = new User();
//                                user.setFbToken(AccessToken.getCurrentAccessToken().getToken());
//                                Gson gson = new Gson();
//                                intent.putExtra("obj", gson.toJson(user));
//                                startActivity(intent);
//                            } else {
//                                Intent intent;
//
//                                if (responseUser.getCard() == null) {
//                                    intent = new Intent(MainActivity.this, PassengerHomeActivity.class);
//                                } else {
////                                    intent = new Intent(MainActivity.this, DriverHomeActivity.class);
//                                    intent = new Intent(MainActivity.this, PassengerHomeActivity.class);
//
//                                }
//
//                                Gson gson = new Gson();
//                                intent.putExtra("obj", gson.toJson(responseUser));
//
//                                startActivity(intent);
//                            }
//
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<User> call, Throwable t) {
//                        }
//
//                    }
//            );


//            Toast.makeText(MainActivity.this, "token " + AccessToken.getCurrentAccessToken().getToken(), Toast.LENGTH_LONG).show();
//
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
