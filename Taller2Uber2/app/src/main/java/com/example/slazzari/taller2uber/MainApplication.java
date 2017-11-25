package com.example.slazzari.taller2uber;

import android.app.Application;
import android.util.Log;

import com.example.slazzari.taller2uber.model.Car;
import com.example.slazzari.taller2uber.model.FacebookToken;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.example.slazzari.taller2uber.networking.interactor.Wikiinteractor;
import com.example.slazzari.taller2uber.networking.repository.Wikirepo;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

/**
 * Created by slazzari on 9/12/17.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LoginManager.getInstance().logOut();
        String token = FirebaseInstanceId.getInstance().getToken().toString();
    }

}
