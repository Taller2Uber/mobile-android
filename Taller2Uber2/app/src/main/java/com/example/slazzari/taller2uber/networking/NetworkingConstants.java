package com.example.slazzari.taller2uber.networking;

/**
 * Created by slazzari on 9/16/17.
 */

public class NetworkingConstants {

//  API
    public static final String BASE_URL = "http://llevame-taller2.herokuapp.com/api/v1/";
//    public static final String BASE_URL = "http://10.0.2.2:3000/api/v1/";
//    public static final String BASE_URL = "http://172.20.10.4/api/v1/";
//    public static final String BASE_URL = "http://172.20.10.2:5000/api/v1/";


    public static final String WIKI = "wiki";
    public static final String USER = "user";
    public static final String LOGIN_USER = "users/login";
    public static final String REGISTER_PASSENGER = "passengers";
    public static final String REGISTER_DRIVER = "drivers";
    public static final String UPDATE_PASSENGER = "passengers/{passenger}";
    public static final String UPDATE_DRIVER = "drivers/{driver}";


    public static final String ROUTES = "routes";
    public static final String PASSENGER_CONFIRM_ROUTE = "routes/confirm";
    public static final String AUTHORIZATION_KEY = "authorization";
    public static final String AVAILABLE_ROUTES = "routes/availables";
    public static final String REQUEST_ROUTE = "requestroute/{route}";
    public static String authToken;


//  FIREBASE
    public static final String FIREBASE_BASE_URL = "https://fcm.googleapis.com/fcm/";
    public static final String FIREBASE_SEND = "send";
    public static final String FIREBASE_SERVER_TOKEN = "key=AAAAc3lcLr8:APA91bEjf0y6NSLjfjvPmbDT0kyadEtyu3KK7TLZ9QHG97LpIr9mhdmuE1DHlzkF_8MzPjNJSwNCilfYBkUgoBkQJUBYssqzJMeI0KYBzR0UbgHbAdJxZWEH-dCGxRodFzQtEwjtdV5-";
    public static final String FIREBASE_SERVER_TOKEN_KEY = "Authorization:";


//  NOTIFICATION
    public static final String NOTIFICATION_TYPE_CHAT_MESSAGE = "chat_message";
    public static final String NOTIFICATION_TYPE_DRIVER_CONFIRM_ROUTE = "driverConfirmedRoute";
    public static final String NOTIFICATION_TYPE_PASSENGER_CONFIRMED_DELIVERY = "passengerConfirmedDriver";
    public static final String NOTIFICATION_TYPE_PASSENGER_REJECTED_DRIVER = "passengerRejectedDriver";
    public static final String NOTIFICATION_TYPE_DRIVER_STARTED_ROUTE = "driverStartedRoute";
    public static final String NOTIFICATION_TYPE_DRIVER_FINISHED_ROUTE = "driverFinishedRoute";

}
