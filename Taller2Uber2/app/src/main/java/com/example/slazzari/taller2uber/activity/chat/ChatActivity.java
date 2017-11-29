package com.example.slazzari.taller2uber.activity.chat;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.LoginActivity;
import com.example.slazzari.taller2uber.activity.home.driver.DriverHomeActivity;
import com.example.slazzari.taller2uber.model.chat.ChatManager;
import com.example.slazzari.taller2uber.model.chat.ChatMessage;
import com.example.slazzari.taller2uber.model.chat.MessageReceiver;

public class ChatActivity extends AppCompatActivity implements MessageReceiver {

    private ChatManager chatManager;
    private EditText chatEditText;
    private RecyclerView chatRecyclerView;
    private ChatRecyclerViewAdapter chatRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatManager = ChatManager.getInstance();

        chatManager.setMessageDelegate(this);

        chatRecyclerView = (RecyclerView) findViewById(R.id.chat_recycler_view);
            chatRecyclerViewAdapter = new ChatRecyclerViewAdapter(chatManager.getMessages());
        chatRecyclerView.setAdapter(chatRecyclerViewAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(ChatActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        chatRecyclerView.setLayoutManager(llm);


        chatEditText = (EditText) findViewById(R.id.chat_edit_text);
        chatEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    ChatMessage message = new ChatMessage(chatEditText.getText().toString());
                    chatManager.postMessage(message);
                    chatEditText.setText("");
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public void onMessageReceibe(ChatMessage message) {
        chatRecyclerViewAdapter.setMessages(chatManager.getMessages());
    }
}
