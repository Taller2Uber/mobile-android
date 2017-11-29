package com.example.slazzari.taller2uber.activity.home.driver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.LoginActivity;
import com.example.slazzari.taller2uber.model.login.LoginManager;
import com.google.gson.Gson;

public class DriverDescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_description);

        logoutButton = (Button) findViewById(R.id.driver_description_logout_button);
        logoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Gson gson = new Gson();

        switch (view.getId()) {
            case R.id.driver_description_logout_button:
                new LoginManager().logout();

                Intent intent = new Intent(DriverDescriptionActivity.this, LoginActivity.class);

                startActivity(intent);
                finish();
                break;
        }
    }
}
