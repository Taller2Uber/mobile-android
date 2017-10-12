package com.example.slazzari.taller2uber.model;

/**
 * Created by slazzari on 10/11/17.
 */

public class Car {


    private String model;
    private String brand;
    private int year;
    private String licensePlate;
    private Boolean ac;

    public Car() {

    }

    public Car(String model, String brand, int year, String licensePlate, Boolean ac) {
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.licensePlate = licensePlate;
        this.ac = ac;
    }
}
