package com.example.slazzari.taller2uber.activity;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.Register.RegisterActivity;
import com.example.slazzari.taller2uber.activity.home.driver.DriverHomeActivity;
import com.example.slazzari.taller2uber.activity.home.passenger.PassengerHomeActivity;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.model.login.LoginManager;
import com.example.slazzari.taller2uber.model.login.LoginManagerResponse;
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

        new LoginManager().login(
                new LoginManagerResponse() {
                    @Override
                    public void onLoginWithUser(User user) {
                        Intent intent;
                        if (user.isPassenger()) {
                            intent = new Intent(MainActivity.this, PassengerHomeActivity.class);
                        } else {
                            intent = new Intent(MainActivity.this, DriverHomeActivity.class);
                        }

                        Gson gson = new Gson();
                        intent.putExtra("obj", gson.toJson(user));

                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onLoginWithFailure() {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
