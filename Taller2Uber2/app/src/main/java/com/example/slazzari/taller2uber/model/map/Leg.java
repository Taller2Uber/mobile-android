package com.example.slazzari.taller2uber.model.map;

import java.util.List;

/**
 * Created by slazzari on 11/28/17.
 */

public class Leg {
    private List<Step> steps;


    private LegLocation startLocation;
    private LegLocation endLocation;

    private LegDistance distance;

    public List<Step> getSteps() {
        return steps;
    }
}
