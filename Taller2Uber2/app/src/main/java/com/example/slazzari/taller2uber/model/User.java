package com.example.slazzari.taller2uber.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slazzari on 9/16/17.
 */

public class User {



    private String firstName;
    private String lastName;
    private String gender;
    private Card card;
    private ArrayList<Car> cars;
    private String ssId;
    private String firebaseToken;

    private String state;

    private String latitude;
    private String longitude;

    private String type;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Card getCard() {
        return card;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public String getSsId() {
        return ssId;
    }

    public void setSsId(String ssId) {
        this.ssId = ssId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFbToken() {
        return fbToken;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    private String fbToken;
    private String password;
    private String userName;


    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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


    public String getGender() {
        return gender;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public boolean isDriver() {
        return this.type.equals("driver");
    }

    public boolean isPassenger() {
        return this.type.equals("passenger");
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName+ '\'' +
                ", facebookToken='" + fbToken + '\'' +
                ", gender=" + gender +
                '}';
    }
}
