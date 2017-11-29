package com.example.slazzari.taller2uber.activity.home.driver;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.model.map.Route;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DriverViewRouteActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Route route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_view_route);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        Gson gson = new Gson();

        String stringRoute = getIntent().getStringExtra("obj");
        route = gson.fromJson(stringRoute, Route.class);

        mapFragment.getMapAsync(this);

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


        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (List<LatLng>polyline : route.getPolyline()) {
            for (LatLng position : polyline) {
                builder.include(position);
            }
        }

        List<List<LatLng>> polyline = route.getPolyline();

        LatLng origin = polyline.get(0).get(0);
        LatLng destination = polyline.get(polyline.size() -1).get(polyline.get(polyline.size() -1).size() -1);

        MarkerOptions markerOrigin = new MarkerOptions().position(origin).title("Origen").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        MarkerOptions markerDestination = new MarkerOptions().position(destination).title("Destino").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        mMap.addMarker(markerOrigin);
        mMap.addMarker(markerDestination);

        int padding = 50;
        LatLngBounds bounds = builder.build();

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);

        mMap.animateCamera(cu);

        drawRoute(route, Color.DKGRAY);
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
}
