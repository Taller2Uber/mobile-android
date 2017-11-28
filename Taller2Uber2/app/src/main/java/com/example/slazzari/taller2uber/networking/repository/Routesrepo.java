package com.example.slazzari.taller2uber.networking.repository;

import com.example.slazzari.taller2uber.model.map.Routes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.AUTHORIZATION_KEY;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.ROUTES;

/**
 * Created by slazzari on 11/26/17.
 */

public interface Routesrepo {

    @POST(ROUTES)
    Call<Routes> getRoutes(@Header(AUTHORIZATION_KEY) String authorization, @Body Routes routes);

}
