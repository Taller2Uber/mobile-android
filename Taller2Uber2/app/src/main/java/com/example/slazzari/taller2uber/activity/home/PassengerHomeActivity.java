package com.example.slazzari.taller2uber.activity.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.BaseActivity;
import com.example.slazzari.taller2uber.activity.Register.RegisterActivity;
import com.example.slazzari.taller2uber.activity.Register.RegisterPassengerActivity;
import com.example.slazzari.taller2uber.model.User;
import com.google.gson.Gson;

public class PassengerHomeActivity extends BaseActivity implements View.OnClickListener{

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_home);

        Gson gson = new Gson();
        String strUser = getIntent().getStringExtra("obj");
        user = gson.fromJson(strUser, User.class);


        getSupportActionBar().setTitle(user.getName());

        Button editPassangerButton = (Button) findViewById(R.id.passenger_home_edit_passenger_button);
        editPassangerButton.setOnClickListener(this);

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
        }
    }
}
