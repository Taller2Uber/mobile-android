package com.example.slazzari.taller2uber.model;

/**
 * Created by slazzari on 10/11/17.
 */

public class Car {


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    private String model;
    private String brand;
    private int year;
    private String licensePlate;
    private Boolean ac;

    public Car() {

    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Car(String model, String brand, int year, String licensePlate, Boolean ac) {
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.licensePlate = licensePlate;
        this.ac = ac;
    }
}
