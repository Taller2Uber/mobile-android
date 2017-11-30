package com.example.slazzari.taller2uber.model;

/**
 * Created by slazzari on 11/29/17.
 */

public class CurrentUserCredentials {
    private static final CurrentUserCredentials ourInstance = new CurrentUserCredentials();

    public static final String NO_STATE = "NO_STATE";
    public static final String BEGIN_ROUTE = "BEGIN_ROUTE";
    public static final String FINISH_ROUTE = "FINISH_ROUTE";

    private String id;
    private String type;
    private String state = NO_STATE;
    private String routeId;
    private String otherFirebaseToken;

    public String getOtherFirebaseToken() {
        return otherFirebaseToken;
    }

    public void setOtherFirebaseToken(String otherFirebaseToken) {
        this.otherFirebaseToken = otherFirebaseToken;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteId() {
        return routeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static CurrentUserCredentials getInstance() {
        return ourInstance;
    }



    private CurrentUserCredentials() {}

    public boolean isDriver() {
        return this.type.equals("driver");
    }
    public boolean isPassenger() {
        return this.type.equals("passenger");
    }

    public void beginRoute() {
        state = BEGIN_ROUTE;
    }

    public void finishRoute() {
        state = FINISH_ROUTE;
    }

}

