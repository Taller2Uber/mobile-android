package com.example.slazzari.taller2uber.activity.home.passenger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.LoginActivity;
import com.example.slazzari.taller2uber.model.User;
import com.facebook.login.LoginManager;
import com.google.gson.Gson;

public class PassengerDescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private User user;

    private EditText nameEditText;
    private EditText genderEditText;
    private EditText cardNumberEditText;
    private EditText cardExpirationDateEditText;
    private EditText cardCompanyEditText;

    private Button editButton;
    private Button acceptButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_description);

        getSupportActionBar().setTitle("Perfil");

        Gson gson = new Gson();
        String strUser = getIntent().getStringExtra("obj");
        user = gson.fromJson(strUser, User.class);

        editButton = (Button) findViewById(R.id.passenger_description_edit_button);
        acceptButton = (Button) findViewById(R.id.passenger_description_accept_edit_button);
        logoutButton = (Button) findViewById(R.id.passenger_description_logout_button);

        editButton.setOnClickListener(this);
        acceptButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);


        nameEditText = (EditText) findViewById(R.id.passenger_description_name_edit_text);
        genderEditText = (EditText) findViewById(R.id.passenger_description_gender_edit_text);
        cardNumberEditText = (EditText) findViewById(R.id.passenger_description_card_number_edit_text);
        cardExpirationDateEditText = (EditText) findViewById(R.id.passenger_description_card_expiration_edit_text);
        cardCompanyEditText = (EditText) findViewById(R.id.passenger_description_card_company_edit_text);

        nameEditText.setText(user.getFirstName());
        genderEditText.setText(user.getGender());
//        cardNumberEditText.setText(user.getCard().getNumber());
//        cardExpirationDateEditText.setText(user.getCard().getExpirationDate());
//        cardCompanyEditText.setText(user.getCard().getCompany());
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

            case R.id.passenger_description_logout_button:
                SharedPreferences preferences = getBaseContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                String userName = preferences.getString("username",null);
                String password = preferences.getString("password",null);

                if((userName != null) && (password != null)) {
                    editor.remove("username").commit();
                    editor.remove("password").commit();
                    editor.commit();
                }else{
                    LoginManager.getInstance().logOut();
                }

                Intent intent = new Intent(PassengerDescriptionActivity.this, LoginActivity.class);

                startActivity(intent);
                finish();
                break;
        }
    }
}
