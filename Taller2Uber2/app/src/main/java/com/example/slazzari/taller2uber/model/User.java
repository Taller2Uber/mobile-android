package com.example.slazzari.taller2uber.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slazzari on 9/16/17.
 */

public class User {
    private String name;
    private String fbToken;
    private String gender;
    private Card card;
    private ArrayList<Car> cars;
    private String ssId;

    public User() {

    }

    public void setFbToken(String fbToken) {
        this.fbToken = fbToken;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Card getCard() {
        return card;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", facebookToken='" + fbToken + '\'' +
                ", gender=" + gender +
                '}';
    }
}
