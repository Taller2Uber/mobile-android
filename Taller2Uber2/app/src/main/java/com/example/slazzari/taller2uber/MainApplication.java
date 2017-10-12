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

        FacebookToken fbToken = new FacebookToken("EAALQMNov0CkBAI2zaUK6swXQF1MfI8EQI2OaBqvGzlcKOST2Bv6irk4gLnrNrp0oiRCF6nXY4fLHmCvmm4s5IXApLyMeSlZCCwlVUDlkxN7NRUWPGLimvv7r8yCryc9yQQiy3irS5TF5KlwMVucRkfwIFfZAr0KdlzOifdfBu1ZAsZAWon7eiZB0XQrIHb8GPrF3RxnM4xgFdowZAcgzrYsHNuLKqZA9ZAsGJgUZCZAusJJQZDZD");

//        Userinteractor.loginUser(fbToken).enqueue(new Callback<User>() {
//            @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//                        Log.w("Testing", "did success");
//                        User user = response.body();
//                        Log.w("Testing", user.toString());
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//                        Log.w("Testing", "did fail");
//                    }
//                }
//        );

        ArrayList<Car> cars = new ArrayList<Car>();
        Car car = new Car("A1", "Audi", 2017, "eep410", true);


        cars.add(car);
        User driver = User();
        
    }

}
