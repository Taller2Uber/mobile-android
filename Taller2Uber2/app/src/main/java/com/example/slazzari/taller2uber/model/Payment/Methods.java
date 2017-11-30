package com.example.slazzari.taller2uber.model.Payment;

import java.util.List;

/**
 * Created by slazzari on 11/30/17.
 */

public class Methods {

    private float balance;
    private List<Method> paymethods;

    public List<Method> getPaymethods() {
        return paymethods;
    }

    public float getBalance() {
        return balance;
    }

    public Methods(){};

}
