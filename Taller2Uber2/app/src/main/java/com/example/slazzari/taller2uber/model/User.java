package com.example.slazzari.taller2uber.model;

/**
 * Created by slazzari on 9/16/17.
 */

public class User {
    private String name;
    private String sirname;
    private String facebookToken;
    private int age;

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sirname='" + sirname + '\'' +
                ", facebookToken='" + facebookToken + '\'' +
                ", age=" + age +
                '}';
    }
}
