package com.example.slazzari.taller2uber.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.model.User;
import com.google.gson.Gson;

public class RegisterPassengerActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_passenger);

        Gson gson = new Gson();
        String strUser = getIntent().getStringExtra("obj");
        User user = gson.fromJson(strUser, User.class);


        EditText cardNumberEditText = (EditText) findViewById(R.id.register_passenger_card_number_edit_text);
        EditText expirationEditText = (EditText) findViewById(R.id.register_passenger_expiration_card_edit_text);
        EditText companyName = (EditText) findViewById(R.id.register_passenger_company_name_edit_text);

        Button registerDriverButton = (Button) findViewById(R.id.register_passenger_confirm_button);
        registerDriverButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Gson gson = new Gson();

        switch(v.getId())
        {
            case R.id.register_passenger_confirm_button :
//              TODO:  Hacer request generando el passenger

                break;
        }
    }
}
