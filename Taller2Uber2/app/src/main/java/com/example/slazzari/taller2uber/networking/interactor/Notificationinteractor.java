package com.example.slazzari.taller2uber.networking.interactor;

import com.example.slazzari.taller2uber.firebase.FirebaseNotification;
import com.example.slazzari.taller2uber.firebase.FirebaseNotificationBody;
import com.example.slazzari.taller2uber.model.Notification;
import com.example.slazzari.taller2uber.model.chat.ChatMessage;
import com.example.slazzari.taller2uber.model.map.Routes;
import com.example.slazzari.taller2uber.networking.repository.Notificationrepo;
import com.example.slazzari.taller2uber.networking.repository.Routesrepo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.security.GeneralSecurityException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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

    public static okhttp3.Call sendChatMessage(ChatMessage chatMessage) {

        OkHttpClient client = new OkHttpClient();


        Gson gson = new Gson();

        Notification notification = new Notification();
        notification.setType(NOTIFICATION_TYPE_CHAT_MESSAGE);
        notification.setContent(chatMessage.getMessage());

        FirebaseNotificationBody firebaseNotificationBody = new FirebaseNotificationBody();
        firebaseNotificationBody.setBody(notification);



        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"to\":\"ezbJFqlOdo0:APA91bFxKV-oGf30q3_JAW-MoFNKyzH_Nmv80uqj-yIZFMD9am1Xjz7PTsaiDx_OYmQDr8pg9bYQo-SZJHa58bzgew1ra-WXcCdYKd9Y_mq2cn41pGOoltkuyWr8LABuSqTG21mjP_HC\",\"notification\":{\"body\":" + gson.toJson(notification)+"}}");
        Request request = new Request.Builder()
                .url("https://fcm.googleapis.com/fcm/send")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "key=AAAAc3lcLr8:APA91bEjf0y6NSLjfjvPmbDT0kyadEtyu3KK7TLZ9QHG97LpIr9mhdmuE1DHlzkF_8MzPjNJSwNCilfYBkUgoBkQJUBYssqzJMeI0KYBzR0UbgHbAdJxZWEH-dCGxRodFzQtEwjtdV5-")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "8b36b199-53e5-ced2-fecd-2a3a79fe64dc")
                .build();

        return client.newCall(request);


    }

}
