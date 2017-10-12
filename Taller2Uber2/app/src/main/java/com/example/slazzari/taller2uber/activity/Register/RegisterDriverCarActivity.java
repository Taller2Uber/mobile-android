package com.example.slazzari.taller2uber.activity.Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.model.Car;
import com.google.gson.Gson;

public class RegisterDriverCarActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText carBrandEditText;
    private EditText carModelEditText;
    private EditText carYearEditText;
    private EditText carLicensePlateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver_car);

        getSupportActionBar().setTitle("Agrega tu auto");

        carBrandEditText = (EditText)findViewById(R.id.register_driver_car_brand_edit_text);
        carModelEditText = (EditText)findViewById(R.id.register_driver_car_model_edit_text);
        carYearEditText = (EditText)findViewById(R.id.register_driver_car_year_edit_text);
        carLicensePlateEditText = (EditText)findViewById(R.id.register_driver_car_license_plate_edit_text);

        Button clearButton = (Button)findViewById(R.id.register_driver_car_clear_button);
        clearButton.setOnClickListener(this);
        Button addButton = (Button)findViewById(R.id.register_driver_car_add_button);
        addButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_driver_car_add_button:
                Car car = new Car(carModelEditText.getText().toString(), carBrandEditText.getText().toString(), Integer.parseInt(carYearEditText.getText().toString()), carLicensePlateEditText.getText().toString(), true);
                Gson gson = new Gson();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("obj", gson.toJson(car));
                setResult(RegisterDriverCarActivity.RESULT_OK, resultIntent);
                finish();

                break;
            case R.id.register_driver_car_clear_button:
                carBrandEditText.setText("");
                carLicensePlateEditText.setText("");
                carYearEditText.setText("");
                carModelEditText.setText("");
                break;
        }
    }
}
