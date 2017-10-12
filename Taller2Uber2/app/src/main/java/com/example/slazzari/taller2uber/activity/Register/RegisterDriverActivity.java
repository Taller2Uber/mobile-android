package com.example.slazzari.taller2uber.activity.Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.BaseActivity;
import com.example.slazzari.taller2uber.model.Car;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RegisterDriverActivity extends BaseActivity implements View.OnClickListener {

    private ArrayList<Car> cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);

        Button addCarButton = (Button) findViewById(R.id.register_driver_add_car_button);
        addCarButton.setOnClickListener(this);
        cars = new ArrayList<Car>();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Gson gson = new Gson();

        if (resultCode == RESULT_OK) {
            Car car = gson.fromJson(data.getStringExtra("obj"), Car.class);

            cars.add(car);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.register_driver_add_car_button :
                Intent registerCarActivityIntent = new Intent(RegisterDriverActivity.this, RegisterDriverCarActivity.class);
                startActivityForResult(registerCarActivityIntent, 0);
                break;
        }
    }


}
