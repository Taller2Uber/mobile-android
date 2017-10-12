package com.example.slazzari.taller2uber.activity.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.BaseActivity;
import com.example.slazzari.taller2uber.activity.MainActivity;
import com.example.slazzari.taller2uber.activity.home.PassengerHomeActivity;
import com.example.slazzari.taller2uber.model.Card;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.example.slazzari.taller2uber.networking.repository.Userrepo;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

                Card card = new Card(cardNumberEditText.getText().toString(), expirationEditText.getText().toString(), companyName.getText().toString());
                user.setCard(card);


                Userinteractor.registerPassenger(user).enqueue(new Callback<User>() {
                       @Override
                       public void onResponse(Call<User> call, Response<User> response) {
                           User responseUser = response.body();
                           Intent passengerActivityIntent = new Intent(RegisterPassengerActivity.this, PassengerHomeActivity.class);
                           Gson gson = new Gson();
                           passengerActivityIntent.putExtra("obj", gson.toJson(responseUser));

                           startActivity(passengerActivityIntent);
                       }

                       @Override
                       public void onFailure(Call<User> call, Throwable t) {
                           Toast.makeText(RegisterPassengerActivity.this, "No se pudo registrar el pasajero", Toast.LENGTH_LONG).show();
                       }
                    }
                );
        }
    }
}
