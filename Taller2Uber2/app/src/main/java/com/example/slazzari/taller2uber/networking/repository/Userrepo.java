package com.example.slazzari.taller2uber.networking.repository;

import com.example.slazzari.taller2uber.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.LOGIN_USER;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.REGISTER_DRIVER;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.REGISTER_PASSENGER;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.USER;

/**
 * Created by slazzari on 9/16/17.
 */

public interface Userrepo {

    @GET(USER)
    Call<User> getUser();

    @POST(LOGIN_USER)
    Call<User> loginUser(@Body User user);

    @POST(REGISTER_DRIVER)
    Call<User> registerDriver(@Body User user);

    @POST(REGISTER_PASSENGER)
    Call<User> registerPassenger(@Body User user);

}
