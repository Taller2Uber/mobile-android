package com.example.slazzari.taller2uber.model.chat;

import android.app.Notification;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.slazzari.taller2uber.MainApplication;
import com.example.slazzari.taller2uber.networking.interactor.Notificationinteractor;
import com.example.slazzari.taller2uber.networking.repository.Notificationrepo;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
    private List<ChatMessage> messages = new ArrayList<ChatMessage>();
    public String toToken;
    private MessageReceiver messageDelegate;

    public void setMessageDelegate(MessageReceiver messageDelegate) {
        this.messageDelegate = messageDelegate;
    }

    private ChatManager() {}

    public List<ChatMessage> updateMessages() {

        Gson gson = new Gson();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainApplication.getAppContext());
        String messagesString = preferences.getString("chat", null);

        if (messagesString == null) {
            SharedPreferences.Editor editor = preferences.edit();

            editor.putString("chat", gson.toJson(messages));
            editor.commit();

            return messages;
        }

        messages = gson.fromJson(messagesString, messages.getClass());

        return messages;
    }



    public void clearChat() {

    }

    public void postMessage(ChatMessage message) {
        onMessageReceibe(message);

        Notificationinteractor.sendChatMessage(message).enqueue(
                new okhttp3.Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                        String body = response.body().string();
                    }
                }
        );
    }

    @Override
    public void onMessageReceibe(ChatMessage message) {
        messages.add(message);
        updateMessages();

        messageDelegate.onMessageReceibe(message);
    }
}
