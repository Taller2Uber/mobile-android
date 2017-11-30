package com.example.slazzari.taller2uber.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;

import com.example.slazzari.taller2uber.MainApplication;
import com.example.slazzari.taller2uber.activity.MainActivity;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.facebook.places.model.CurrentPlaceRequestParams;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by slazzari on 11/30/17.
 */

public class TrackingLooper {
    private static final TrackingLooper ourInstance = new TrackingLooper();

    private LocationManager locationManager;
    private int refreshTime = 5;
    private Location currentLocation;
    private Handler handler;
    private Runnable runnableCode;

    public static TrackingLooper getInstance() {
        return ourInstance;
    }


    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
                currentLocation = location;
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    public void setup(Context context) {

        locationManager = (LocationManager) MainApplication.getAppContext().getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainApplication.getAppContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, refreshTime, 0, locationListener);

            return;
        }

    }

    public void beginTracking() {
        // Create a Timer task
        handler = new Handler();
// Define the code block to be executed
        runnableCode = new Runnable() {
            @Override
            public void run() {


                if (CurrentUserCredentials.getInstance().getId() == null) {
//                    No user logged, then pass
                } else {

                    if (currentLocation == null) {
//                      Dont log null location
                    } else {

                        User locationUser = new User();
                        locationUser.setLatitude(String.valueOf(currentLocation.getLatitude()));
                        locationUser.setLongitude(String.valueOf(currentLocation.getLongitude()));
                        locationUser.setSsId(CurrentUserCredentials.getInstance().getId());

                        if (CurrentUserCredentials.getInstance().isDriver()) {

                            Userinteractor.updateDriver(locationUser).enqueue(
                                    new Callback<User>() {
                                        @Override
                                        public void onResponse(Call<User> call, Response<User> response) {

                                        }

                                        @Override
                                        public void onFailure(Call<User> call, Throwable t) {

                                        }
                                    }
                            );


                        } else {
                            Userinteractor.updatePassenger(locationUser).enqueue(
                                    new Callback<User>() {
                                        @Override
                                        public void onResponse(Call<User> call, Response<User> response) {

                                        }

                                        @Override
                                        public void onFailure(Call<User> call, Throwable t) {

                                        }
                                    }
                            );
                        }
                    }

                }

                handler.postDelayed(this, refreshTime*1000);
            }
        };
// Start the initial runnable task by posting through the handler
        handler.post(runnableCode);
    }

    public void finishTracking() {
        handler.removeCallbacks(runnableCode);
    }

    private TrackingLooper() {
    }
}
