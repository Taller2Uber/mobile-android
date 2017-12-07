package com.example.slazzari.taller2uber.activity.home.passenger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.BaseActivity;
import com.example.slazzari.taller2uber.activity.PaymentActivity;
import com.example.slazzari.taller2uber.activity.chat.ChatActivity;
import com.example.slazzari.taller2uber.activity.home.driver.DriverHomeActivity;
import com.example.slazzari.taller2uber.model.TrackingLooper;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.model.chat.ChatMessage;
import com.google.gson.Gson;

public class PassengerHomeActivity extends BaseActivity implements View.OnClickListener{

    private User user;

    private Button chatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_home);

        Gson gson = new Gson();
        String strUser = getIntent().getStringExtra("obj");
        user = gson.fromJson(strUser, User.class);


        getSupportActionBar().setTitle(user.getFirstName());

        Button editPassangerButton = (Button) findViewById(R.id.passenger_home_edit_passenger_button);
        editPassangerButton.setOnClickListener(this);

        Button mapPassengerButton = (Button) findViewById(R.id.passenger_home_map_button);
        mapPassengerButton.setOnClickListener(this);

        Button balancePassangerButton = (Button) findViewById(R.id.passenger_home_balance_button);
        balancePassangerButton.setOnClickListener(this);

        chatButton = (Button) findViewById(R.id.passenger_home_chat_button);
        chatButton.setOnClickListener(this);

        TrackingLooper.getInstance().setup(this);
     }

    @Override
    public void onClick(View view) {
        Gson gson = new Gson();

        switch (view.getId()) {
            case R.id.passenger_home_edit_passenger_button:
                Intent passengerActivityIntent = new Intent(PassengerHomeActivity.this, PassengerDescriptionActivity.class);

                passengerActivityIntent.putExtra("obj", gson.toJson(user));
                startActivity(passengerActivityIntent);

                break;
            case R.id.passenger_home_map_button:
                Intent mapActivityIntent = new Intent(PassengerHomeActivity.this, MapsActivity.class);

                mapActivityIntent.putExtra("obj", gson.toJson(user));

                startActivity(mapActivityIntent);

                break;

            case R.id.passenger_home_chat_button:
                Intent chatActivityIntent = new Intent(PassengerHomeActivity.this, ChatActivity.class);

                startActivity(chatActivityIntent);
                break;
            case R.id.passenger_home_balance_button:
                Intent balanceActivityIntent = new Intent(PassengerHomeActivity.this, PaymentActivity.class);

                startActivity(balanceActivityIntent);

        }
    }
}
