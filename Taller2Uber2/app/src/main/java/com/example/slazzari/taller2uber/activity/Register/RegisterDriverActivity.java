package com.example.slazzari.taller2uber.activity.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.BaseActivity;
import com.example.slazzari.taller2uber.activity.home.driver.DriverHomeActivity;
import com.example.slazzari.taller2uber.model.Car;
import com.example.slazzari.taller2uber.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RegisterDriverActivity extends BaseActivity implements View.OnClickListener {

    private ArrayList<Car> cars;
    private User user;
    private RecyclerView carsRecyclerView;
    private RecyclerView.LayoutManager carsLayoutManager;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);

        getSupportActionBar().setTitle("Registracion de conductor");


        Gson gson = new Gson();
        String strUser = getIntent().getStringExtra("obj");
        user = gson.fromJson(strUser, User.class);


        carsRecyclerView = (RecyclerView)findViewById(R.id.register_driver_cars_recycler_view);
        carsLayoutManager = new LinearLayoutManager(this);
        carsRecyclerView.setLayoutManager(carsLayoutManager);

        cars = new ArrayList<Car>();

        mAdapter = new MyAdapter(cars);
        carsRecyclerView.setAdapter(mAdapter);


        Button addCarButton = (Button) findViewById(R.id.register_driver_add_car_button);
        addCarButton.setOnClickListener(this);

        Button continueButton = (Button) findViewById(R.id.register_driver_confirm_button);
        continueButton.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Gson gson = new Gson();

        if (resultCode == RESULT_OK) {
            Car car = gson.fromJson(data.getStringExtra("obj"), Car.class);

            cars.add(car);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {

        Gson gson = new Gson();

        switch(v.getId())
        {
            case R.id.register_driver_add_car_button:
                Intent registerCarActivityIntent = new Intent(RegisterDriverActivity.this, RegisterDriverCarActivity.class);
                startActivityForResult(registerCarActivityIntent, 0);
                break;
            case R.id.register_driver_confirm_button:

                user.setCars(cars);

//                TODO:Hacer el request para generar el usuario

                Intent registerDriverIntent = new Intent(RegisterDriverActivity.this, DriverHomeActivity.class);

                registerDriverIntent.putExtra("obj", gson.toJson(user));

                startActivity(registerDriverIntent);
        }
    }

}
