package com.example.slazzari.taller2uber.firebase;

/**
 * Created by slazzari on 11/29/17.
 */

public class FirebaseNotification {
    public FirebaseNotification() {}

    private String to;
    private FirebaseNotificationBody body;

    public void setBody(FirebaseNotificationBody body) {
        this.body = body;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
