package com.example.slazzari.taller2uber.firebase;

import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.example.slazzari.taller2uber.model.Notification;
import com.example.slazzari.taller2uber.model.chat.ChatManager;
import com.example.slazzari.taller2uber.model.chat.ChatMessage;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.NOTIFICATION_TYPE_DRIVER_CONFIRM_ROUTE;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.NOTIFICATION_TYPE_DRIVER_FINISHED_ROUTE;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.NOTIFICATION_TYPE_DRIVER_STARTED_ROUTE;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.NOTIFICATION_TYPE_PASSENGER_CONFIRMED_DELIVERY;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.NOTIFICATION_TYPE_PASSENGER_REJECTED_DRIVER;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    final private String TAG = "MyFirebaseMessaging";
    public MyFirebaseMessagingService() {
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // ...

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.w(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.w(TAG, "Message data payload: " + remoteMessage.getData());
        }

//        // Check if message contains a notification payload.
        String notificationBody = "";
        if (remoteMessage.getNotification() != null) {
            Log.w(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            notificationBody = remoteMessage.getNotification().getBody();
        }

        Gson gson = new Gson();

        Notification notification = gson.fromJson(notificationBody, Notification.class);

        if (notification.isChatMessage()) {
            ChatMessage message = new ChatMessage(notification.getContent());

            ChatManager.getInstance().onMessageReceibe(message);
        }

        if (notification.isDriverConfirmedRoute()) {

        }

        if (notification.isPassengerConfirmedDriver()) {

        }

        if (notification.isPassengerRejectedRoute()) {

        }

        if (notification.isDriverStartedRoute()) {

        }

        if (notification.isDriverFinishedRoute()) {

        }

        Log.w(TAG, "From: " + notificationBody);
    }

}
