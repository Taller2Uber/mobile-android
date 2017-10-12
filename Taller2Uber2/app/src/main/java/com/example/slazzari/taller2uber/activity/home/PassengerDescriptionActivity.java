package com.example.slazzari.taller2uber.activity.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.model.User;
import com.google.gson.Gson;

public class PassengerDescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private User user;

    private EditText nameEditText;
    private EditText genderEditText;
    private EditText cardNumberEditText;
    private EditText cardExpirationDateEditText;
    private EditText cardCompanyEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_description);

        getSupportActionBar().setTitle("Perfil");

        Gson gson = new Gson();
        String strUser = getIntent().getStringExtra("obj");
        user = gson.fromJson(strUser, User.class);

        Button editButton = (Button) findViewById(R.id.passenger_description_edit_button);
        Button acceptButton = (Button) findViewById(R.id.passenger_description_accept_edit_button);

        editButton.setOnClickListener(this);
        acceptButton.setOnClickListener(this);

        nameEditText = (EditText) findViewById(R.id.passenger_description_name_edit_text);
        genderEditText = (EditText) findViewById(R.id.passenger_description_gender_edit_text);
        cardNumberEditText = (EditText) findViewById(R.id.passenger_description_card_number_edit_text);
        cardExpirationDateEditText = (EditText) findViewById(R.id.passenger_description_card_expiration_edit_text);
        cardCompanyEditText = (EditText) findViewById(R.id.passenger_description_card_company_edit_text);

        nameEditText.setText(user.getName());
        genderEditText.setText(user.getGender());
        cardNumberEditText.setText(user.getCard().getNumber());
        cardExpirationDateEditText.setText(user.getCard().getExpirationDate());
        cardCompanyEditText.setText(user.getCard().getCompany());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.passenger_description_edit_button:
                nameEditText.setEnabled(true);
                genderEditText.setEnabled(true);
                cardNumberEditText.setEnabled(true);
                cardExpirationDateEditText.setEnabled(true);
                cardCompanyEditText.setEnabled(true);

                break;
            case R.id.passenger_description_accept_edit_button:
                nameEditText.setEnabled(false);
                genderEditText.setEnabled(false);
                cardNumberEditText.setEnabled(false);
                cardExpirationDateEditText.setEnabled(false);
                cardCompanyEditText.setEnabled(false);

                break;
        }
    }
}
