package com.example.slazzari.taller2uber.model;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.NOTIFICATION_TYPE_CHAT_MESSAGE;

/**
 * Created by slazzari on 11/28/17.
 */

public class Notification {

    public Notification(){}
    private String type;
    private String content;

    public String getContent() {
        return content;
    }

    public boolean isChatMessage() {
        return type.equals(NOTIFICATION_TYPE_CHAT_MESSAGE);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(String type) {
        this.type = type;
    }
}
