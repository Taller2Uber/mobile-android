package com.example.slazzari.taller2uber.model.chat;

import android.app.Notification;

import com.example.slazzari.taller2uber.networking.interactor.Notificationinteractor;
import com.example.slazzari.taller2uber.networking.repository.Notificationrepo;

import java.util.List;

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
        Notificationinteractor.sendChatMessage(message).enqueue(
                new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        int reponseCode = response.code();

                        String responseStirng = response.body();

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                }
        );

    }

    @Override
    public void onMessageReceibe(ChatMessage message) {
        messageDelegate.onMessageReceibe(message);
    }
}
