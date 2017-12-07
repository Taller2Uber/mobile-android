package com.example.slazzari.taller2uber.activity.home.driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.BaseActivity;
import com.example.slazzari.taller2uber.activity.Register.MyAdapter;
import com.example.slazzari.taller2uber.model.Car;
import com.example.slazzari.taller2uber.model.CurrentUserCredentials;
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

        getSupportActionBar().setTitle("Autos");


        Gson gson = new Gson();
        String strUser = getIntent().getStringExtra("obj");
        user = gson.fromJson(strUser, User.class);


        carsRecyclerView = (RecyclerView)findViewById(R.id.register_driver_cars_recycler_view);
        carsLayoutManager = new LinearLayoutManager(this);
        carsRecyclerView.setLayoutManager(carsLayoutManager);


        cars = user.getCars() == null ? new ArrayList<Car>() : user.getCars();

        mAdapter = new MyAdapter(cars);
        carsRecyclerView.setAdapter(mAdapter);


        Button addCarButton = (Button) findViewById(R.id.register_driver_add_car_button);
        addCarButton.setOnClickListener(this);

        if (!CurrentUserCredentials.getInstance().getId().equals(user.getSsId())) {
            addCarButton.setVisibility(View.GONE);
        }

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
        }
    }

}
