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

        FacebookToken fbToken = new FacebookToken("EAALQMNov0CkBAHASTZBqTFw5ZCJ8Ve7itjRMRwHwfdrGFax9cEUKIBiNIo2j9AB82twdzA3dBNgS20mJ0R0LaV0C1LcrfW7FZBu99hLFv4QtCWEyzD695xRoZCLtP99aRtBnPyAOVFIfLSIHuZBo7UmtAWNC8hbRo29oXdCu7sqD4OWZCm6PYUReoIS2f5PKJnnZBLZAsFmyXl0Sgl2hdTAEGIp8OmJdnRITVXUBrgOyQRCKUPbtz9IT");


        ArrayList<Car> cars = new ArrayList<Car>();
        Car car = new Car("A1", "Audi", 2017, "eep410", true);


        cars.add(car);
        User driver = new User();

//        Userinteractor.registerDriver(driver).enqueue(new Callback<User>() {
//                                                      @Override
//                                                      public void onResponse(Call<User> call, Response<User> response) {
//                                                          Log.w("Testing", "did success");
//                                                          User user = response.body();
//                                                          Log.w("Testing", user.toString());
//                                                      }
//
//                                                      @Override
//                                                      public void onFailure(Call<User> call, Throwable t) {
//                                                          Log.w("Testing", "did fail");
//                                                      }
//                                                  }
//        );


    }

}
