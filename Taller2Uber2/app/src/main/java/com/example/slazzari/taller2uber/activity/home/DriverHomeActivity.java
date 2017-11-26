package com.example.slazzari.taller2uber.activity.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.slazzari.taller2uber.R;
import com.google.gson.Gson;

public class DriverHomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button editDriverButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);

        editDriverButton = (Button) findViewById(R.id.driver_home_edit_passenger_button);
        editDriverButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Gson gson = new Gson();

        switch (view.getId()) {
            case R.id.driver_home_edit_passenger_button:
                Intent passengerActivityIntent = new Intent(DriverHomeActivity.this, DriverDescriptionActivity.class);

//                passengerActivityIntent.putExtra("obj", gson.toJson(user));

                startActivity(passengerActivityIntent);

                break;
        }
    }
}