package com.example.slazzari.taller2uber.networking.repository;

import com.example.slazzari.taller2uber.firebase.FirebaseNotification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.FIREBASE_SEND;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.FIREBASE_SERVER_TOKEN;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.FIREBASE_SERVER_TOKEN_KEY;

/**
 * Created by slazzari on 11/29/17.
 */

public interface Notificationrepo {

    @Headers(FIREBASE_SERVER_TOKEN+FIREBASE_SERVER_TOKEN_KEY)
    @POST(FIREBASE_SEND)
    Call<String> sendNotification(@Body FirebaseNotification notification);
}
