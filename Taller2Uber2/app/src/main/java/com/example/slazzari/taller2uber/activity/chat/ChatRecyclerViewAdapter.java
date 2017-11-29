package com.example.slazzari.taller2uber.activity.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.home.driver.AvailableRoutesRecyclerViewAdapter;
import com.example.slazzari.taller2uber.model.chat.ChatMessage;
import com.example.slazzari.taller2uber.model.map.AvailableRoute;

import java.util.List;

/**
 * Created by slazzari on 11/29/17.
 */

public class ChatRecyclerViewAdapter extends
        RecyclerView.Adapter<ChatRecyclerViewAdapter.ChatRecyclerViewHolder>{

    private List<ChatMessage> messages;

    public ChatRecyclerViewAdapter(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;

        notifyDataSetChanged();
    }

    @Override
    public ChatRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_chat_message,parent, false);
        return new ChatRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChatRecyclerViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        holder.message = message;
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ChatRecyclerViewHolder extends RecyclerView.ViewHolder {
        public ChatMessage message;



        public ChatRecyclerViewHolder(View view) {
            super(view);

        }

    }

}
