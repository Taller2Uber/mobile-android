package com.example.slazzari.taller2uber.model.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.slazzari.taller2uber.MainApplication;
import com.example.slazzari.taller2uber.activity.LoginActivity;
import com.example.slazzari.taller2uber.activity.Register.RegisterActivity;
import com.example.slazzari.taller2uber.activity.home.driver.DriverHomeActivity;
import com.example.slazzari.taller2uber.activity.home.passenger.PassengerHomeActivity;
import com.example.slazzari.taller2uber.model.CurrentUserCredentials;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.networking.NetworkingConstants;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.facebook.AccessToken;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.AUTHORIZATION_KEY;

/**
 * Created by slazzari on 11/29/17.
 */

public class LoginManager  {

    public void login(LoginManagerResponse loginResponse) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainApplication.getAppContext());

        final String userName = preferences.getString("username",null);
        final String password = preferences.getString("password",null);

        if ((userName != null) && (password != null)) {
            login(userName, password, loginResponse);
        } else {
            if (AccessToken.getCurrentAccessToken() != null) {
                String fbToken = AccessToken.getCurrentAccessToken().getToken();
                login(fbToken, loginResponse);
            } else {
                loginResponse.onLoginWithFailure();
            }
        }
    }

    public void login(String userName, String password, final LoginManagerResponse loginResponse) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        login(user,loginResponse);
    }

    public void login(String fbToken, LoginManagerResponse loginResponse) {
        User user = new User();
        user.setFbToken(fbToken);

        login(user, loginResponse);
    }

    public void login(final User user, final LoginManagerResponse loginResponse) {
        Userinteractor.loginUser(user).enqueue(
                new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        User responseUser = response.body();

                        String authorization = response.headers().get(AUTHORIZATION_KEY);
                        NetworkingConstants.authToken = authorization;

                        if (response.code() != 200) {
                            loginResponse.onLoginWithFailure();
                            return;
                        } else {
                            if (responseUser == null) {
                                loginResponse.onLoginWithFailure();
                                return;
                            } else {
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainApplication.getAppContext());
                                SharedPreferences.Editor editor = preferences.edit();

                                editor.putString("username", user.getUserName());
                                editor.putString("password",user.getPassword());

                                editor.commit();

                                CurrentUserCredentials.getInstance().setId(user.getSsId());
                                CurrentUserCredentials.getInstance().setType(user.getType());

                                loginResponse.onLoginWithUser(responseUser);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        loginResponse.onLoginWithFailure();
                    }
                }
        );
    }

    public void logout() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainApplication.getAppContext());
        SharedPreferences.Editor editor = preferences.edit();

        String userName = preferences.getString("username",null);
        String password = preferences.getString("password",null);

        if((userName != null) && (password != null)) {
            editor.remove("username").commit();
            editor.remove("password").commit();
            editor.commit();
        }else{
            com.facebook.login.LoginManager.getInstance().logOut();
        }

        CurrentUserCredentials.getInstance().setId(null);
        CurrentUserCredentials.getInstance().setType(null);
    }
}
