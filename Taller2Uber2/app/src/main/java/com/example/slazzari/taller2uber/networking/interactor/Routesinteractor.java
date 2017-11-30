package com.example.slazzari.taller2uber.networking.interactor;

import com.example.slazzari.taller2uber.model.map.AvailableRoute;
import com.example.slazzari.taller2uber.model.map.PassengerAcceptedRoute;
import com.example.slazzari.taller2uber.model.map.PassengerConfirmRoute;
import com.example.slazzari.taller2uber.model.map.Route;
import com.example.slazzari.taller2uber.model.map.Routes;
import com.example.slazzari.taller2uber.networking.repository.Routesrepo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import java.util.List;

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

    public static Call<AvailableRoute> getRoute(String routeId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();


        Routesrepo routesrepo = retrofit.create(Routesrepo.class);

        return routesrepo.getRoute(routeId, authToken);
    }

    public static Call<PassengerConfirmRoute> passengerConfirmRoute(PassengerConfirmRoute confirmRoute) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();


        Routesrepo routesrepo = retrofit.create(Routesrepo.class);

        return routesrepo.passengerConfirmRoutes(authToken, confirmRoute);
    }

    public static Call<List<AvailableRoute>> getAvailableRoutes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();


        Routesrepo routesrepo = retrofit.create(Routesrepo.class);

        return routesrepo.getAvailableRoutes(authToken);
    }

    public static Call<AvailableRoute> driverConfritmRoute(AvailableRoute route) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Routesrepo routesrepo = retrofit.create(Routesrepo.class);

        return routesrepo.driverConfirmRoute(route.getId(), authToken, route);
    }

    public static Call<Void> passengerAcceptRoute(String routId, boolean passengerAcceptRoute) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Routesrepo routesrepo = retrofit.create(Routesrepo.class);

        PassengerAcceptedRoute acceptedRoute = new PassengerAcceptedRoute();
        acceptedRoute.setAccepted(passengerAcceptRoute);

        return routesrepo.passengerAcceptRouteRequest(routId, authToken, acceptedRoute);
    }

    public static Call<Void> driverStartRoute(String routId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Routesrepo routesrepo = retrofit.create(Routesrepo.class);

        return routesrepo.driverStartRoute(routId, authToken);
    }

    public static Call<Void> driverFinishRoute(String routId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Routesrepo routesrepo = retrofit.create(Routesrepo.class);

        return routesrepo.driverFinishRoute(routId, authToken);
    }



}
