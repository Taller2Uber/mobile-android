package com.example.slazzari.taller2uber.model.Payment;

import java.util.HashMap;

/**
 * Created by slazzari on 11/30/17.
 */

public class Method {

    private String paymethod;
    private HashMap<String, String> parameters;
    private float amount;

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public float getAmount() {
        return amount;
    }

    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public Method() {}


}
