package com.example.slazzari.taller2uber.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.Register.RegisterActivity;
import com.example.slazzari.taller2uber.model.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

public class MainActivity extends BaseActivity {
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LoginButton loginButton = (LoginButton) this.findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // Other app specific specialization

        // Callback registration
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {


            @Override
            public void onSuccess(LoginResult loginResult) {
//                TODO:if (el token está registrado)
//                  TODO:entrar derecho a la home con ese usuario

    //                else
//                  Registrar el usuario

//                if (el token está registrado)

//                else
                Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);

                User user = new User();
                user.setFbToken("EAALQMNov0CkBAI2zaUK6swXQF1MfI8EQI2OaBqvGzlcKOST2Bv6irk4gLnrNrp0oiRCF6nXY4fLHmCvmm4s5IXApLyMeSlZCCwlVUDlkxN7NRUWPGLimvv7r8yCryc9yQQiy3irS5TF5KlwMVucRkfwIFfZAr0KdlzOifdfBu1ZAsZAWon7eiZB0XQrIHb8GPrF3RxnM4xgFdowZAcgzrYsHNuLKqZA9ZAsGJgUZCZAusJJQZDZD");
                Gson gson = new Gson();
                intent.putExtra("obj", gson.toJson(user));
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Login cancel", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(MainActivity.this, "Login error", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
