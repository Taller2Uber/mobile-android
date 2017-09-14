package com.example.slazzari.taller2uber.networking.repository;

import com.example.slazzari.taller2uber.model.Wiki;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by slazzari on 9/14/17.
 */

public interface Wikirepo {

    @GET("wiki/Teorema_maestro")
    Call<Wiki> getWiki();
}
