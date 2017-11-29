package com.example.slazzari.taller2uber;

import android.app.Application;
import android.content.Context;

import com.facebook.login.LoginManager;
import com.google.firebase.FirebaseApp;

/**
 * Created by slazzari on 9/12/17.
 */

public class MainApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MainApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MainApplication.context;
    }
}
