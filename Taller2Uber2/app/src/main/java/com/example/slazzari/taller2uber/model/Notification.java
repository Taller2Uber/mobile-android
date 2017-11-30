package com.example.slazzari.taller2uber.model;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.NOTIFICATION_TYPE_CHAT_MESSAGE;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.NOTIFICATION_TYPE_DRIVER_CONFIRM_ROUTE;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.NOTIFICATION_TYPE_DRIVER_FINISHED_ROUTE;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.NOTIFICATION_TYPE_DRIVER_STARTED_ROUTE;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.NOTIFICATION_TYPE_PASSENGER_CONFIRMED_DELIVERY;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.NOTIFICATION_TYPE_PASSENGER_REJECTED_DRIVER;

/**
 * Created by slazzari on 11/28/17.
 */

public class Notification {

    public Notification(){}
    private String type;
    private String content;

    public String getContent() {
        return content;
    }

    public boolean isChatMessage() {
        return type.equals(NOTIFICATION_TYPE_CHAT_MESSAGE);
    }
    public boolean isDriverConfirmedRoute() {return type.equals(NOTIFICATION_TYPE_DRIVER_CONFIRM_ROUTE);}
    public boolean isPassengerConfirmedDriver() {return type.equals(NOTIFICATION_TYPE_PASSENGER_CONFIRMED_DELIVERY);}
    public boolean isPassengerRejectedRoute() {return type.equals(NOTIFICATION_TYPE_PASSENGER_REJECTED_DRIVER);}
    public boolean isDriverStartedRoute() {return type.equals(NOTIFICATION_TYPE_DRIVER_STARTED_ROUTE);}
    public boolean isDriverFinishedRoute() {return  type.equals(NOTIFICATION_TYPE_DRIVER_FINISHED_ROUTE);}

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(String type) {
        this.type = type;
    }
}
