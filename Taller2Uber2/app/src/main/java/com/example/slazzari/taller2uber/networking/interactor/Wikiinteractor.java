package com.example.slazzari.taller2uber.networking.interactor;

import com.example.slazzari.taller2uber.model.Wiki;
import com.example.slazzari.taller2uber.networking.repository.Wikirepo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.BASE_URL;

/**
 * Created by slazzari on 9/14/17.
 */

public class Wikiinteractor {

    public static Call<Wiki> getWiki() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Wikirepo wikirepo = retrofit.create(Wikirepo.class);
        return wikirepo.getWiki();
    }

}
