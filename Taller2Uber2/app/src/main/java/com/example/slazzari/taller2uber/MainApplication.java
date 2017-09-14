package com.example.slazzari.taller2uber;

import android.app.Application;
import android.util.Log;

import com.example.slazzari.taller2uber.networking.interactor.Wikiinteractor;
import com.example.slazzari.taller2uber.networking.repository.Wikirepo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

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

        Log.w("Testing", Wikiinteractor.getWiki().toString());
    }


}
