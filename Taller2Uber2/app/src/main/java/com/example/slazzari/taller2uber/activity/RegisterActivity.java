package com.example.slazzari.taller2uber.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.model.User;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerDriverButton = (Button) findViewById(R.id.register_driver_button);
        registerDriverButton.setOnClickListener(this);

        Button registerPassengerButton = (Button) findViewById(R.id.register_passenger_button);
        registerPassengerButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.register_driver_button :
                Toast.makeText(RegisterActivity.this,"Driver button was tapped", 1000).show();
                Intent driverActiviryIntent = new Intent(RegisterActivity.this, RegisterDriverActivity.class);
                startActivity(driverActiviryIntent);

                break;
            case R.id.register_passenger_button :
                Toast.makeText(RegisterActivity.this,"Passenger button was tapped", 1000).show();
                Intent passengerActivityIntent = new Intent(RegisterActivity.this, RegisterPassengerActivity.class);
                startActivity(passengerActivityIntent);
                break;
        }
    }
}
