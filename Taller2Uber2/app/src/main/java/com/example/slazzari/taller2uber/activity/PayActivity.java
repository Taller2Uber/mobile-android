package com.example.slazzari.taller2uber.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.slazzari.taller2uber.R;
import com.google.gson.Gson;

public class PayActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView payFieldsRecyclerView;
    private Button payButton;

    private String routeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);


        Gson gson = new Gson();
        routeId = getIntent().getStringExtra("obj");

        payButton = (Button) findViewById(R.id.pay_button);
        payButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pay_button:

        }
    }
}
