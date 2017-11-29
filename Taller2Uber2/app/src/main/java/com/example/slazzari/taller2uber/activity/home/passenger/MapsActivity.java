package com.example.slazzari.taller2uber.activity.home.passenger;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.slazzari.taller2uber.model.map.PassengerConfirmRoute;
import com.example.slazzari.taller2uber.model.map.Route;
import com.example.slazzari.taller2uber.model.map.Routes;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.networking.interactor.Routesinteractor;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.slazzari.taller2uber.R;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

import android.support.v4.content.ContextCompat;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.slazzari.taller2uber.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int MY_LOCATION_REQUEST_CODE = 1;
    private MarkerOptions markerOrigin;
    private MarkerOptions markerDestination;
    private User user;
    private Routes routes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Gson gson = new Gson();
        String strUser = getIntent().getStringExtra("obj");
        user = gson.fromJson(strUser, User.class);




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
        Toast.makeText(MapsActivity.this, "Seleccionar un lugar de pickup", Toast.LENGTH_LONG).show();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_LOCATION_REQUEST_CODE);
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng clickCoords) {

                for (Route route : routes.getRoutes()) {
                    for (List<LatLng> leg: route.getPolyline())
                        for (LatLng polyCoords : leg) {
                            float[] results = new float[1];
                            Location.distanceBetween(clickCoords.latitude, clickCoords.longitude,
                                    polyCoords.latitude, polyCoords.longitude, results);

                            if (results[0] < 100) {
                                // If distance is less than 100 meters, this is your polyline
                                PassengerConfirmRoute confirmRoute = new PassengerConfirmRoute(route, routes.getPassengerId());

                                Routesinteractor.passengerConfirmRoute(confirmRoute).enqueue(new Callback<PassengerConfirmRoute>() {
                                    @Override
                                    public void onResponse(Call<PassengerConfirmRoute> call, Response<PassengerConfirmRoute> response) {
                                        PassengerConfirmRoute confirmRoute1 = response.body();

                                        Toast.makeText(MapsActivity.this, "Su solicitud de viaje fue completada", Toast.LENGTH_LONG).show();
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<PassengerConfirmRoute> call, Throwable t) {
                                        Toast.makeText(MapsActivity.this, "No se pudo solicitar su viaje, intente nuevemante", Toast.LENGTH_LONG).show();

                                    }
                                });
                                return;
                            }
                        }
                }
            }
        });



        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng point) {

                MarkerOptions markerOptions = new MarkerOptions().position(point).title("Custom location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                mMap.addMarker(markerOptions);

                if (markerOrigin == null) {
                    markerOrigin = markerOptions;
                    Toast.makeText(MapsActivity.this, "Seleccionar un lugar de destino", Toast.LENGTH_LONG).show();
                } else {

                    markerDestination = markerOptions;
                    Routes routes = new Routes();

                    routes.setOriginAndDestination(markerOrigin.getPosition(), markerDestination.getPosition());
                    routes.setPassengerId(user.getSsId());


                    Routesinteractor.getRoutes(routes).enqueue(
                            new Callback<Routes>() {
                                @Override
                                public void onResponse(Call<Routes> call, Response<Routes> response) {
                                    Routes routes = response.body();
                                    routes.setPassengerId(user.getSsId());

                                    drawRoutes(routes);
                                }

                                @Override
                                public void onFailure(Call<Routes> call, Throwable t) {
                                    Toast.makeText(MapsActivity.this, "Error", Toast.LENGTH_LONG).show();
                                }
                            }
                    );
                }
            }
        });
    }

    private void drawRoute(Route route, Integer color) {
        ArrayList<LatLng> points = null;
        PolylineOptions lineOptions = null;

        points = new ArrayList<LatLng>();
        lineOptions = new PolylineOptions();

        List<List<LatLng>> polylines = route.getPolyline();

        for (List<LatLng>polyline : polylines) {
            for (LatLng position : polyline) {
                points.add(position);
            }
        }

        lineOptions.addAll(points);
        lineOptions.width(10);

        lineOptions.color(color);


        mMap.addPolyline(lineOptions);
    }

    private void drawRoutes(Routes routes) {
        this.routes = routes;
        List<Integer> colors = new ArrayList<Integer>();
        colors.add(new Integer(Color.GRAY));
        colors.add(new Integer(Color.DKGRAY));
        colors.add(new Integer(Color.LTGRAY));
        List<Route> routesList = routes.getRoutes();
        for (int i = 0; i<routesList.size(); i++) {
            Route route = routesList.get(i);
            Integer color = colors.get(i % routesList.size());
            drawRoute(route, color);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                } else {
                    ActivityCompat.requestPermissions(MapsActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_LOCATION_REQUEST_CODE);
                }
            }
        }
    }
}
