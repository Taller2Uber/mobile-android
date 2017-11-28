package com.example.slazzari.taller2uber.model.map;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slazzari on 11/28/17.
 */

public class Route {
    private List<Leg> legs;

    public List<Leg> getLegs() {
        return legs;
    }

    public List<List<LatLng>> getPolyline() {
        List<List<LatLng>> polylines = new ArrayList<List<LatLng>>();
        for (Leg leg : getLegs()) {
            for (Step step : leg.getSteps()) {
                polylines.add(step.getPolyline().decode());
            }
        }
        return polylines;
    }

}
