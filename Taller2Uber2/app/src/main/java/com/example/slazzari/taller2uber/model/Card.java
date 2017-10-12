package com.example.slazzari.taller2uber.model;

/**
 * Created by slazzari on 10/11/17.
 */

public class Card {
    private String number;
    private String expirationDate;
    private String company;

    public Card () {

    }

    public Card(String number, String expirationDate, String company) {
        this.number = number;
        this.expirationDate = expirationDate;
        this.company = company;
    }

    @Override
    public String toString() {
        return "Card{" +
                "number='" + number + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
