package com.example.slazzari.taller2uber.model.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by slazzari on 11/26/17.
 */

public class Routes {

    public Routes() {}

    private List<Route> routes;
    private String passengerId;

    private String longitudeOrigin;
    private String latitudeOrigin;

    private String latitudeDestination;
    private String longitudeDestination;


    public void setOriginAndDestination(LatLng origin, LatLng destination) {
        latitudeOrigin = new Double(origin.latitude).toString();
        longitudeOrigin = new Double(origin.longitude).toString();
        latitudeDestination = new Double(destination.latitude).toString();
        longitudeDestination = new Double(destination.longitude).toString();
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getLatitudeOrigin() {
        return latitudeOrigin;
    }

    public void setLatitudeOrigin(String latitudeOrigin) {
        this.latitudeOrigin = latitudeOrigin;
    }

    public String getLongitudeOrigin() {
        return longitudeOrigin;
    }

    public void setLongitudeOrigin(String longitudeOrigin) {
        this.longitudeOrigin = longitudeOrigin;
    }

    public String getLatitudeDestination() {
        return latitudeDestination;
    }

    public void setLatitudeDestination(String latitudeDestination) {
        this.latitudeDestination = latitudeDestination;
    }

    public String getLongitudeDestination() {
        return longitudeDestination;
    }

    public void setLongitudeDestination(String longitudeDestination) {
        this.longitudeDestination = longitudeDestination;
    }


}
