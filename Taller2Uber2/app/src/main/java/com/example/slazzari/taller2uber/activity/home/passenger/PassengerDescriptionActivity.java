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
import com.example.slazzari.taller2uber.model.login.LoginManager;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerDescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private User user;

    private EditText nameEditText;
    private EditText genderEditText;
    private EditText lastnameEditText;


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
        lastnameEditText = (EditText) findViewById(R.id.passenger_description_lastname_edit_text);

        nameEditText.setText(user.getFirstName());
        genderEditText.setText(user.getGender());
        lastnameEditText.setText(user.getLastName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.passenger_description_edit_button:
                nameEditText.setEnabled(true);
                genderEditText.setEnabled(true);
                lastnameEditText.setEnabled(true);
                break;
            case R.id.passenger_description_accept_edit_button:
                user.setFirstName(nameEditText.getText().toString());
                user.setLastName(lastnameEditText.getText().toString());

                Userinteractor.updatePassenger(user).enqueue(
                        new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                user = response.body();
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {

                            }
                        }
                );

                nameEditText.setEnabled(false);
                genderEditText.setEnabled(false);
                lastnameEditText.setEnabled(false);
                break;

            case R.id.passenger_description_logout_button:
                new LoginManager().logout();
                Intent intent = new Intent(PassengerDescriptionActivity.this, LoginActivity.class);

                startActivity(intent);
                finish();
                break;
        }
    }
}
