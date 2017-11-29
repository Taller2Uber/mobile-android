package com.example.slazzari.taller2uber.networking;

/**
 * Created by slazzari on 9/16/17.
 */

public class NetworkingConstants {
    public static final String BASE_URL = "http://llevame-taller2.herokuapp.com/api/v1/";
//    public static final String BASE_URL = "http://10.0.2.2:3000/api/v1/";
//    public static final String BASE_URL = "http://172.20.10.4/api/v1/";
//    public static final String BASE_URL = "http://192.168.8.210:5000/api/v1/";

    public static final String WIKI = "wiki";
    public static final String USER = "user";
    public static final String LOGIN_USER = "users/login";
    public static final String REGISTER_PASSENGER = "passengers";
    public static final String REGISTER_DRIVER = "drivers";
    public static final String ROUTES = "routes";
    public static final String PASSENGER_CONFIRM_ROUTE = "routes/confirm";
    public static final String AUTHORIZATION_KEY = "authorization";
    public static final String AVAILABLE_ROUTES = "availableroutes";
    public static final String REQUEST_ROUTE = "requestroute/{route}";
    public static String authToken;

}
