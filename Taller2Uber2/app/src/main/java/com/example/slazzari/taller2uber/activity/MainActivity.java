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


        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);

        User user = new User();
        user.setFbToken("EAALQMNov0CkBAKnn9BX8aVL3qmZCWpQ75Tox5ZBQw9BHBPXZCMlCmZAZCcRan8J4UxCsYFFZA0NnPhxba6ZBEJzrUsmgQ8ZBMGtLohmsBD0eo0tAv7bGVcdXhGGNppQBuxDnEE0v9XYoMYKZAWOY3LgZC6NLG4OV5yxgJ4ikmZB0UeP1VXGPdNZADRxk5ZBn16kZBhjBVA57ZBYKw0w7p4SY1T4AYZAC3T6XDgv1CNnZBVYm2jNAStgZDZD");
        Gson gson = new Gson();
        intent.putExtra("obj", gson.toJson(user));
        startActivity(intent);


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
                user.setFbToken("EAALQMNov0CkBAA9Wzw9bj3CvjstiKl5gO191HUZCA8xmZBU3ZBfD58JJmolio8wzYG7OBVRFFK46d7uXDU9eUl5VaITtA6Qc0mI7JRliSyAapa9M6wyvK2ZCNasYTyamOlswzMfe0wm2ZCF9VCLOSLpIhrEYzTHBOgSZAZBZBbZAYCM2NZCpAZAh1mQT1ZCkIZBz4CAWTx9TwAfyfUpkz1c3xGmpYdDJWp75sRZB6jQOZC0ZBrpVqiNZCMZA7PYXZB6");
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
