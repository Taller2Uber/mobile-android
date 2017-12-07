package com.example.slazzari.taller2uber.activity.home.driver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.LoginActivity;
import com.example.slazzari.taller2uber.model.CurrentUserCredentials;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.model.login.LoginManager;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverDescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logoutButton;

    private EditText nameEditText;
    private EditText genderEditText;
    private EditText lastnameEditText;

    private Button editButton;
    private Button acceptButton;
    private Button showCarsButton;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_description);

        Gson gson = new Gson();
        String strUser = getIntent().getStringExtra("obj");
        user = gson.fromJson(strUser, User.class);

        editButton = (Button) findViewById(R.id.driver_description_edit_button);
        acceptButton = (Button) findViewById(R.id.driver_description_accept_edit_button);
        logoutButton = (Button) findViewById(R.id.driver_description_logout_button);
        showCarsButton = (Button) findViewById(R.id.driver_description_show_cars_button);


        editButton.setOnClickListener(this);
        acceptButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
        showCarsButton.setOnClickListener(this);

        nameEditText = (EditText) findViewById(R.id.driver_description_name_edit_text);
        genderEditText = (EditText) findViewById(R.id.driver_description_gender_edit_text);
        lastnameEditText = (EditText) findViewById(R.id.driver_description_lastname_edit_text);

        if (!CurrentUserCredentials.getInstance().getId().equals(user.getSsId())) {
            editButton.setVisibility(View.GONE);
            acceptButton.setVisibility(View.GONE);
            logoutButton.setVisibility(View.GONE);
        }

        nameEditText.setText(user.getFirstName());
        genderEditText.setText(user.getGender());
        lastnameEditText.setText(user.getLastName());
    }

    @Override
    public void onClick(View view) {
        Gson gson = new Gson();

        switch (view.getId()) {

            case R.id.driver_description_edit_button:
                nameEditText.setEnabled(true);
                genderEditText.setEnabled(true);
                lastnameEditText.setEnabled(true);
                break;
            case R.id.driver_description_accept_edit_button:
                user.setFirstName(nameEditText.getText().toString());
                user.setLastName(lastnameEditText.getText().toString());

                Userinteractor.updateDriver(user).enqueue(
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
            case R.id.driver_description_logout_button:
                new LoginManager().logout();

                Intent intent = new Intent(DriverDescriptionActivity.this, LoginActivity.class);

                startActivity(intent);
                finish();
                break;
            case R.id.driver_description_show_cars_button:
                Intent carIntent = new Intent(DriverDescriptionActivity.this, RegisterDriverActivity.class);

                startActivity(carIntent);
                break;
        }
    }
}
