package com.example.slazzari.taller2uber.networking.interactor;

import com.example.slazzari.taller2uber.model.map.Routes;
import com.example.slazzari.taller2uber.networking.repository.Routesrepo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.BASE_URL;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.authToken;

/**
 * Created by slazzari on 11/26/17.
 */

public class Routesinteractor {

    public static Call<Routes> getRoutes(Routes routes) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();


        Routesrepo routesrepo = retrofit.create(Routesrepo.class);

        return routesrepo.getRoutes(authToken, routes);
    }
}
