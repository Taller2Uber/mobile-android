package com.example.slazzari.taller2uber.activity.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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


        getSupportActionBar().setTitle(user.getFirstName());

        Button editPassangerButton = (Button) findViewById(R.id.passenger_home_edit_passenger_button);
        editPassangerButton.setOnClickListener(this);

        Button mapPassengerButton = (Button) findViewById(R.id.passenger_home_map_button);
        mapPassengerButton.setOnClickListener(this);

        EditText searchEdidText = (EditText) findViewById(R.id.passenger_home_search_edittext);
        searchEdidText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    Hacer el request de los camino
                    Toast.makeText(PassengerHomeActivity.this, "Did return", Toast.LENGTH_LONG).show();
                    Intent mapActivityIntent = new Intent(PassengerHomeActivity.this, MapsActivity.class);

                    startActivity(mapActivityIntent);

                    return true;
                }

                return false;
            }
        });
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
        }
    }
}
