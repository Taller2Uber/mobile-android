package com.example.slazzari.taller2uber.model.chat;

import android.app.Notification;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.example.slazzari.taller2uber.MainApplication;
import com.example.slazzari.taller2uber.model.CurrentUserCredentials;
import com.example.slazzari.taller2uber.networking.interactor.Notificationinteractor;
import com.example.slazzari.taller2uber.networking.repository.Notificationrepo;
import com.google.gson.Gson;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/*
* Encargado de administrar la comunicacion del chat entre el dispositivo y otro
* dispositivo.
*
* */
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

    /*
    * Devuelve los mensajes almacenadops en los shared preferences
    * */
    public List<ChatMessage> getMessages() {
        Gson gson = new Gson();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainApplication.getAppContext());
        String messagesString = preferences.getString("chat", null);

        try {
            List<ChatMessage> sharedMessages = gson.fromJson(messagesString, messages.getClass());

            if (sharedMessages == null) {
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("chat", gson.toJson(messages));
                editor.commit();
            }

        } catch (Exception e) {
            SharedPreferences.Editor editor = preferences.edit();

            editor.putString("chat", gson.toJson(messages));
            editor.commit();
        }

        messagesString = preferences.getString("chat", null);

        Type messagesType = new TypeToken<List<ChatMessage>>(){}.getType();

        List<ChatMessage> messagesFromGson = gson.fromJson(messagesString, messagesType);
        messages = messagesFromGson;
        return messages;
    }

    /*
    * Agrega un mensaje para ser almacenado en los shared preferences
    * */
    public List<ChatMessage> addMessage(ChatMessage message) {
        Gson gson = new Gson();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainApplication.getAppContext());
        messages = getMessages();
        messages.add(message);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("chat", gson.toJson(messages));
        editor.commit();

        return messages;
    }


    /*
    * Limpia el chat de todos los mensajes
    * */
    public void clearChat() {
        messages.removeAll(messages);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainApplication.getAppContext());
        Gson gson = new Gson();
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("chat", gson.toJson(messages));
        editor.commit();

    }

    /*
    * Publica un mensaje a ser enviado al destinatario
    * */
    public void postMessage(ChatMessage message) {
        onMessageReceibe(message);

        Notificationinteractor.sendChatMessage(message, CurrentUserCredentials.getInstance().getOtherFirebaseToken()).enqueue(
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

    /*
    * Este método se llama cuando un mensaje llega, ya sea por parte de un usuario remoto o del
    * mismo dispositivo
    * */
    @Override
    public void onMessageReceibe(ChatMessage message) {
        addMessage(message);

        Handler mainHandler = new Handler(MainApplication.getAppContext().getMainLooper());
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                if (messageDelegate!= null) {
                    messageDelegate.onMessageReceibe(new ChatMessage());
                }
            }
        };
        mainHandler.post(myRunnable);
    }
}
