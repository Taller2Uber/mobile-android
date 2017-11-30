package com.example.slazzari.taller2uber.model.Payment;

import java.util.HashMap;

/**
 * Created by slazzari on 11/30/17.
 */

public class Method {

    private String paymethod;
    private HashMap<String, String> parameters;
    private float ammount;

    public void setAmmount(float ammount) {
        this.ammount = ammount;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public Method() {}


}
