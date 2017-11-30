package com.example.slazzari.taller2uber.networking.repository;

import com.example.slazzari.taller2uber.activity.home.driver.AvailableRoutesRecyclerViewAdapter;
import com.example.slazzari.taller2uber.model.map.AvailableRoute;
import com.example.slazzari.taller2uber.model.map.PassengerAcceptedRoute;
import com.example.slazzari.taller2uber.model.map.PassengerConfirmRoute;
import com.example.slazzari.taller2uber.model.map.Route;
import com.example.slazzari.taller2uber.model.map.Routes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.AUTHORIZATION_KEY;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.AVAILABLE_ROUTES;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.PASSENGER_CONFIRM_ROUTE;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.PASSENGER_CONFIRM_ROUTE_REQUEST;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.REQUEST_ROUTE;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.ROUTE;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.ROUTES;

/**
 * Created by slazzari on 11/26/17.
 */

public interface Routesrepo {

    @POST(ROUTES)
    Call<Routes> getRoutes(@Header(AUTHORIZATION_KEY) String authorization, @Body Routes routes);

    @GET(ROUTE)
    Call<AvailableRoute> getRoute(@Path("route") String string, @Header(AUTHORIZATION_KEY) String authorization);

    @POST(PASSENGER_CONFIRM_ROUTE)
    Call<PassengerConfirmRoute> passengerConfirmRoutes(@Header(AUTHORIZATION_KEY) String authorization, @Body PassengerConfirmRoute confirmRoute);

    @GET(AVAILABLE_ROUTES)
    Call<List<AvailableRoute>> getAvailableRoutes(@Header(AUTHORIZATION_KEY) String authorization);

    @GET()

    @POST(REQUEST_ROUTE)
    Call<AvailableRoute> driverConfirmRoute(@Path("route") String route, @Header(AUTHORIZATION_KEY) String authorization, @Body AvailableRoute availableRoute);

    @POST(PASSENGER_CONFIRM_ROUTE_REQUEST)
    Call<Void> passengerAcceptRouteRequest(@Path("route") String string, @Header(AUTHORIZATION_KEY) String authorization, @Body PassengerAcceptedRoute acceptRoute);

}
