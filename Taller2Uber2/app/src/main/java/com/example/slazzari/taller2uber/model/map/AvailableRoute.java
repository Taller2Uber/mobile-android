package com.example.slazzari.taller2uber.model.map;

/**
 * Created by slazzari on 11/28/17.
 */

public class AvailableRoute {

    public AvailableRoute() {}

    private Route route;

    public Route getRoute() {
        return route;
    }

    private String status;
    private int passengerId;
    private AvailableRouteId _id;
    private int driverId;

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    private class AvailableRouteId {
        private String $oid;

        public String get$oid() {
            return $oid;
        }
    }

    public String getId() {
        return _id.get$oid();
    }

}
