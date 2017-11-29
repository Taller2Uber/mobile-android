package com.example.slazzari.taller2uber.model.chat;

import android.app.Notification;

import com.example.slazzari.taller2uber.networking.interactor.Notificationinteractor;
import com.example.slazzari.taller2uber.networking.repository.Notificationrepo;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatManager implements MessageReceiver {

    private static final ChatManager ourInstance = new ChatManager();

    public static ChatManager getInstance() {
        return ourInstance;
    }
    private List<ChatMessage> messages;
    public String toToken;
    private MessageReceiver messageDelegate;

    public void setMessageDelegate(MessageReceiver messageDelegate) {
        this.messageDelegate = messageDelegate;
    }

    private ChatManager() {}

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void clearChat() {
//        TODO: implement method
    }

    public void postMessage(ChatMessage message) {

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"to\": \"dFUsCR3KbKw:APA91bGCf9XOniAWV-MblDvnTvi_vYuLMBCquNnSCmfVWsTN3yM-lSeE_sxtBFfc92Bk2GI3PNA46eeAiFqAilh4h39BvK-fP20u7dekMSPHCHe-NmXhxVg8nuZVGA8lUjw5z9PcGFfF\",\n    \"notification\" : {\n    \t\"body\":\"chupame un huevo\"\n    }\n}");
        Request request = new Request.Builder()
                .url("https://fcm.googleapis.com/fcm/send")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "key=AAAAc3lcLr8:APA91bEjf0y6NSLjfjvPmbDT0kyadEtyu3KK7TLZ9QHG97LpIr9mhdmuE1DHlzkF_8MzPjNJSwNCilfYBkUgoBkQJUBYssqzJMeI0KYBzR0UbgHbAdJxZWEH-dCGxRodFzQtEwjtdV5-")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "8b36b199-53e5-ced2-fecd-2a3a79fe64dc")
                .build();



        client.newCall(request).enqueue(
                new okhttp3.Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                    }
                }
            );
        }

//        Notificationinteractor.sendChatMessage(message).enqueue(
//                new Callback<String>() {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        int reponseCode = response.code();
//
//                        String responseStirng = response.body();
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//
//                    }
//                }
//        );

    @Override
    public void onMessageReceibe(ChatMessage message) {
        messageDelegate.onMessageReceibe(message);
    }
}
