package com.example.slazzari.taller2uber.model;

/**
 * Created by slazzari on 11/29/17.
 */

public class CurrentUserCredentials {
    private static final CurrentUserCredentials ourInstance = new CurrentUserCredentials();
    private String id;
    private String type;

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

    private CurrentUserCredentials() {
    }

    public boolean isDriver() {
        return this.type.equals("driver");
    }
    public boolean isPassenger() {
        return this.type.equals("passenger");
    }

}
