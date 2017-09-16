package com.example.slazzari.taller2uber.networking.repository;

import com.example.slazzari.taller2uber.model.Wiki;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.WIKI;

/**
 * Created by slazzari on 9/14/17.
 */

public interface Wikirepo {

    @GET(WIKI)
    Call<Wiki> getWiki();
}
