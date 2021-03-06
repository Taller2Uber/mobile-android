package com.example.slazzari.taller2uber.firebase;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Credentials;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.ConfirmActivity;
import com.example.slazzari.taller2uber.activity.PaymentActivity;
import com.example.slazzari.taller2uber.activity.home.driver.DriverViewRouteActivity;
import com.example.slazzari.taller2uber.model.CurrentUserCredentials;
import com.example.slazzari.taller2uber.model.Notification;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.model.chat.ChatManager;
import com.example.slazzari.taller2uber.model.chat.ChatMessage;
import com.example.slazzari.taller2uber.model.map.AvailableRoute;
import com.example.slazzari.taller2uber.networking.interactor.Routesinteractor;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        String titleNotification = "";
        String messageNotification = "";


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

        boolean sendPush = false;

        Gson gson = new Gson();

        Notification notification = gson.fromJson(notificationBody, Notification.class);

        if (notification.isChatMessage()) {
            ChatMessage message = new ChatMessage(notification.getContent());

            message.setFrom(0);

            ChatManager.getInstance().onMessageReceibe(message);
        }

        if (notification.isDriverConfirmedRoute()) {
            Intent intent = new Intent (this, ConfirmActivity.class);

            intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("obj", notification.getContent());
            startActivity(intent);

            titleNotification = "Aviso de ruta";
            messageNotification = "El conductor confirmo tu solicitud de viaje";


            sendPush = true;
        }

        if (notification.isPassengerConfirmedDriver()) {
            CurrentUserCredentials.getInstance().setRouteId(notification.getContent());

            Routesinteractor.getRoute(notification.getContent()).enqueue(
                    new Callback<AvailableRoute>() {
                        @Override
                        public void onResponse(Call<AvailableRoute> call, Response<AvailableRoute> response) {
                            AvailableRoute availableRoute = response.body();

                            Userinteractor.getPassenger(String.valueOf(availableRoute.getPassengerId())).enqueue(
                                    new Callback<User>() {
                                        @Override
                                        public void onResponse(Call<User> call, Response<User> response) {
                                            CurrentUserCredentials.getInstance().setOtherFirebaseToken(response.body().getFirebaseToken());
                                        }

                                        @Override
                                        public void onFailure(Call<User> call, Throwable t) {
                                        }
                                    }
                            );
                        }

                        @Override
                        public void onFailure(Call<AvailableRoute> call, Throwable t) {

                        }
                    }
            );
            titleNotification = "Aviso de ruta";
            messageNotification = "El pasajero confirmo tu solicitud de viaje";

            sendPush = true;
        }

        if (notification.isPassengerRejectedRoute()) {
            titleNotification = "Aviso de ruta";
            messageNotification = "El pasajero rechazó tu solicitud de viaje";
            sendPush = true;
        }

        if (notification.isDriverStartedRoute()) {
            titleNotification = "Aviso de ruta";
            messageNotification = "El conductor compenzó la ruta";

            sendPush = true;
        }

        if (notification.isDriverFinishedRoute()) {
            titleNotification = "Aviso de ruta";
            messageNotification = "El conductor finalizo la ruta";

            Intent intent = new Intent (this, PaymentActivity.class);

            intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("obj", notification.getContent());
            startActivity(intent);

            sendPush = true;
        }

        if (notification.isSeparationExceeded()) {
            titleNotification = "Aviso de ruta";
            messageNotification = "Se alejaron mucho el conductor y el pasajero :(";

            sendPush = true;
        }

        if (sendPush) {
            int mNotificationId = 001;
            NotificationManager mNotifyMgr =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(titleNotification)
                    .setContentText(messageNotification)
                    .setAutoCancel(false);

            mNotifyMgr.notify(mNotificationId, mBuilder.build());

        }
    }

}
