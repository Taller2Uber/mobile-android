package com.example.slazzari.taller2uber.networking.repository;

import com.example.slazzari.taller2uber.model.User;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.USER;

/**
 * Created by slazzari on 9/16/17.
 */

public interface Userrepo {

    @GET(USER)
    Call<User> getUser();

}
