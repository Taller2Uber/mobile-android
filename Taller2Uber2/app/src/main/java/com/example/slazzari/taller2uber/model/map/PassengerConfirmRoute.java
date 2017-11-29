package com.example.slazzari.taller2uber.model.map;

/**
 * Created by slazzari on 11/28/17.
 */

public class PassengerConfirmRoute {

    public PassengerConfirmRoute(){}

    public PassengerConfirmRoute(Route route, String passengerId){
        this.route = route;
        this.passengerId = passengerId;
    }

    private Route route;
    private String passengerId;
}
