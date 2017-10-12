package com.example.slazzari.taller2uber.activity.Register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.BaseActivity;
import com.example.slazzari.taller2uber.activity.home.PassengerHomeActivity;
import com.example.slazzari.taller2uber.model.Card;
import com.example.slazzari.taller2uber.model.User;
import com.google.gson.Gson;

public class RegisterPassengerActivity extends BaseActivity implements View.OnClickListener {

    private User user;

    private EditText cardNumberEditText;
    private EditText expirationEditText;
    private EditText companyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_passenger);

        Gson gson = new Gson();
        String strUser = getIntent().getStringExtra("obj");
        user = gson.fromJson(strUser, User.class);


        cardNumberEditText = (EditText) findViewById(R.id.register_passenger_card_number_edit_text);
        expirationEditText = (EditText) findViewById(R.id.register_passenger_expiration_card_edit_text);
        companyName = (EditText) findViewById(R.id.register_passenger_company_name_edit_text);

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

                Card card = new Card(cardNumberEditText.getText().toString(), expirationEditText.getText().toString(), companyName.getText().toString());
                user.setCard(card);

                Intent passengerActivityIntent = new Intent(RegisterPassengerActivity.this, PassengerHomeActivity.class);
                passengerActivityIntent.putExtra("obj", gson.toJson(user));

                startActivity(passengerActivityIntent);

                break;
        }
    }
}
