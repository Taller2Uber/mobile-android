package com.example.slazzari.taller2uber;

import android.app.Application;

import com.facebook.login.LoginManager;

/**
 * Created by slazzari on 9/12/17.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LoginManager.getInstance().logOut();
//        String token = FirebaseInstanceId.getInstance().getToken().toString();
    }

}
