package com.example.slazzari.taller2uber.model.chat;

/**
 * Created by slazzari on 11/28/17.
 */

public class ChatMessage {
    private String message;
    private int from;

    public ChatMessage(String message) {
        this.message = message;
    };
    public ChatMessage(){}

    public void setFrom(int from) {
        this.from = from;
    }

    public int getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }
}
