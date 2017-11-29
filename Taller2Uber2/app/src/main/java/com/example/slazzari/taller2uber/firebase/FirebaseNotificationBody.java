package com.example.slazzari.taller2uber.firebase;

import com.example.slazzari.taller2uber.model.Notification;

/**
 * Created by slazzari on 11/29/17.
 */

public class FirebaseNotificationBody {

    private Notification body;

    public FirebaseNotificationBody() {}

    public void setBody(Notification body) {
        this.body = body;
    }
}
