package com.example.slazzari.taller2uber.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.Register.RegisterActivity;
import com.example.slazzari.taller2uber.activity.home.driver.DriverHomeActivity;
import com.example.slazzari.taller2uber.activity.home.passenger.PassengerHomeActivity;
import com.example.slazzari.taller2uber.model.TrackingLooper;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.model.login.LoginManager;
import com.example.slazzari.taller2uber.model.login.LoginManagerResponse;
import com.example.slazzari.taller2uber.networking.NetworkingConstants;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.AUTHORIZATION_KEY;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginManagerResponse {

    private CallbackManager callbackManager;

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Login");

        setContentView(R.layout.activity_login);

        LoginButton loginButton = (LoginButton) this.findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions("email");

        Button loginNativeButton = (Button) this.findViewById(R.id.login_button);
        loginNativeButton.setOnClickListener(this);

        Button registerButton = (Button) this.findViewById(R.id.register_button);
        registerButton.setOnClickListener(this);

        usernameEditText = (EditText) this.findViewById(R.id.username_edit_text);
        passwordEditText = (EditText) this.findViewById(R.id.password_edit_text);

        // Other app specific specialization

        // Callback registration
        callbackManager = CallbackManager.Factory.create();

        TrackingLooper.getInstance().setup(this);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                String fbToken = loginResult.getAccessToken().getToken();
                new LoginManager().login(fbToken, LoginActivity.this);
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login cancelado", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(LoginActivity.this, "Error en el login q", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.register_button:

                final User user = new User();

                user.setUserName(usernameEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                Gson gson = new Gson();
                intent.putExtra("obj", gson.toJson(user));
                startActivity(intent);
                finish();

                break;
            case R.id.login_button:
                new LoginManager().login(usernameEditText.getText().toString(), passwordEditText.getText().toString(), this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onLoginWithUser(User user) {
        Intent intent;
        if (user.isPassenger()) {
            intent = new Intent(LoginActivity.this, PassengerHomeActivity.class);
        } else {
            intent = new Intent(LoginActivity.this, DriverHomeActivity.class);
        }

        Gson gson = new Gson();
        intent.putExtra("obj", gson.toJson(user));

        startActivity(intent);
        finish();

    }

    @Override
    public void onLoginWithFailure() {
        Toast.makeText(LoginActivity.this, "No se pudo hacer el login", Toast.LENGTH_LONG).show();
    }
}
