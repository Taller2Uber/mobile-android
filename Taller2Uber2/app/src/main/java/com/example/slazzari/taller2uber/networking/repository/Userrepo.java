package com.example.slazzari.taller2uber.networking.repository;

import com.example.slazzari.taller2uber.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.AUTHORIZATION_KEY;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.DRIVER;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.LOGIN_USER;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.PASSENGER;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.REGISTER_DRIVER;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.REGISTER_PASSENGER;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.UPDATE_DRIVER;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.UPDATE_PASSENGER;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.USER;

/**
 * Created by slazzari on 9/16/17.
 */

public interface Userrepo {

    @GET(USER)
    Call<User> getUser();


    @GET(PASSENGER)
    Call<User> getPassenger(@Path("passenger") String passengerId, @Header(AUTHORIZATION_KEY) String authorization);

    @GET(DRIVER)
    Call<User> getDriver(@Path("driver") String passengerId, @Header(AUTHORIZATION_KEY) String authorization);



    @POST(LOGIN_USER)
    Call<User> loginUser(@Body User user);

    @POST(REGISTER_DRIVER)
    Call<User> registerDriver(@Body User user);

    @POST(REGISTER_PASSENGER)
    Call<User> registerPassenger(@Body User user);




    @PUT(UPDATE_PASSENGER)
    Call<User> updatePassenger(@Body User user, @Path("passenger") String passengerId, @Header(AUTHORIZATION_KEY) String authorization);

    @PUT(UPDATE_DRIVER)
    Call<User> updateDriver(@Body User user, @Path("driver") String driverId, @Header(AUTHORIZATION_KEY) String authorization);

}
