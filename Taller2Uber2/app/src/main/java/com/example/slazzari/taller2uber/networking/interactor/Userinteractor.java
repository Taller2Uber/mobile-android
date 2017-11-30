package com.example.slazzari.taller2uber.networking.interactor;

import com.example.slazzari.taller2uber.model.Payment.Amount;
import com.example.slazzari.taller2uber.model.Payment.Method;
import com.example.slazzari.taller2uber.model.Payment.Methods;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.networking.repository.Userrepo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.BASE_URL;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.authToken;

/**
 * Created by slazzari on 9/16/17.
 */

public class Userinteractor {

    public static Call<User> getUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Userrepo userrepo= retrofit.create(Userrepo.class);

        return userrepo.getUser();
    }

    public static Call<User> getPassenger(String passengerId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Userrepo userrepo= retrofit.create(Userrepo.class);

        return userrepo.getPassenger(passengerId, authToken);
    }

    public static Call<User> getDriver(String driverId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Userrepo userrepo= retrofit.create(Userrepo.class);

        return userrepo.getDriver(driverId, authToken);
    }



    public static Call<User> loginUser(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Userrepo userrepo= retrofit.create(Userrepo.class);

        return userrepo.loginUser(user);
    }

    public static Call<User> registerPassenger(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Userrepo userrepo= retrofit.create(Userrepo.class);

        return userrepo.registerPassenger(user);
    }

    public static Call<User> registerDriver(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Userrepo userrepo= retrofit.create(Userrepo.class);

        return userrepo.registerDriver(user);
    }

    public static Call<User> updatePassenger(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Userrepo userrepo= retrofit.create(Userrepo.class);

        String ssid = user.getSsId();
        user.setSsId(null);

        return userrepo.updatePassenger(user, ssid, authToken);
    }

    public static Call<User> updateDriver(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Userrepo userrepo= retrofit.create(Userrepo.class);

        String ssid = user.getSsId();
        user.setSsId(null);


        return userrepo.updateDriver(user, ssid, authToken);
    }

    public static Call<Void> userPay(User user, Method method) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Userrepo userrepo= retrofit.create(Userrepo.class);

        String ssid = user.getSsId();
        user.setSsId(null);


        return userrepo.pay(method, ssid, authToken);
    }

    public static Call<Methods> userDebt(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Userrepo userrepo= retrofit.create(Userrepo.class);

        String ssid = user.getSsId();
        user.setSsId(null);

        return userrepo.debt(ssid, authToken);
    }
}
