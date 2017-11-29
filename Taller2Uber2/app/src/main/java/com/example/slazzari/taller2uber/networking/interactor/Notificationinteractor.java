package com.example.slazzari.taller2uber.networking.interactor;

import com.example.slazzari.taller2uber.firebase.FirebaseNotification;
import com.example.slazzari.taller2uber.firebase.FirebaseNotificationBody;
import com.example.slazzari.taller2uber.model.Notification;
import com.example.slazzari.taller2uber.model.chat.ChatMessage;
import com.example.slazzari.taller2uber.model.map.Routes;
import com.example.slazzari.taller2uber.networking.repository.Notificationrepo;
import com.example.slazzari.taller2uber.networking.repository.Routesrepo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.BASE_URL;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.FIREBASE_BASE_URL;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.NOTIFICATION_TYPE_CHAT_MESSAGE;
import static com.example.slazzari.taller2uber.networking.NetworkingConstants.authToken;

/**
 * Created by slazzari on 11/29/17.
 */

public class Notificationinteractor {

    public static Call<String> sendChatMessage(ChatMessage chatMessage) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FIREBASE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Notification notification = new Notification();
        notification.setType(NOTIFICATION_TYPE_CHAT_MESSAGE);
        notification.setContent("hola como estas");

        FirebaseNotificationBody firebaseNotificationBody = new FirebaseNotificationBody();
        firebaseNotificationBody.setBody(notification);

        FirebaseNotification firebaseNotification = new FirebaseNotification();
        firebaseNotification.setTo("dFUsCR3KbKw:APA91bGCf9XOniAWV-MblDvnTvi_vYuLMBCquNnSCmfVWsTN3yM-lSeE_sxtBFfc92Bk2GI3PNA46eeAiFqAilh4h39BvK-fP20u7dekMSPHCHe-NmXhxVg8nuZVGA8lUjw5z9PcGFfF");
        firebaseNotification.setBody(firebaseNotificationBody);


        Notificationrepo notificationrepo = retrofit.create(Notificationrepo.class);

        return notificationrepo.sendNotification(firebaseNotification);
    }

}
