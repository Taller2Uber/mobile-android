package com.example.slazzari.taller2uber.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.home.driver.AvailableRoutesRecyclerViewAdapter;
import com.example.slazzari.taller2uber.model.CurrentUserCredentials;
import com.example.slazzari.taller2uber.model.Payment.Method;
import com.example.slazzari.taller2uber.model.Payment.Methods;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.example.slazzari.taller2uber.networking.repository.Userrepo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView payFieldsRecyclerView;
    private Button payButton;
    private LinearLayoutManager payLayoutManager;
    private PayAdapter payAdapter;
    private List<String> parameters;


    private Method method;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        payFieldsRecyclerView = (RecyclerView)findViewById(R.id.pay_fields_recycler_view);


        payLayoutManager = new LinearLayoutManager(this);
        payFieldsRecyclerView.setLayoutManager(payLayoutManager);

        Gson gson = new Gson();

        method = gson.fromJson(getIntent().getStringExtra("obj"), Method.class);

        payButton = (Button) findViewById(R.id.pay_button);
        payButton.setOnClickListener(this);

        HashMap<String, String> paremetersHash = method.getParameters();

        parameters = new ArrayList<String>();
        for (Iterator i = paremetersHash.keySet().iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            String value = (String) paremetersHash.get(key);

            parameters.add(key);
        }

        payAdapter = new PayAdapter(parameters);
        payFieldsRecyclerView.setAdapter(payAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pay_button:
                List<String> editedParameters = new ArrayList<>();
                for(int i = 0 ; i < payAdapter.getItemCount() ; i++) {
                    PayAdapter.ViewHolder viewHolder = (PayAdapter.ViewHolder) payFieldsRecyclerView.findViewHolderForAdapterPosition(i);

                    if (viewHolder == null) {
                        continue;
                    }

                    editedParameters.add(viewHolder.editText.getText().toString());
                }



                HashMap<String, String> editedParametersHash = new HashMap<>();

                for (int i = 0 ; i < parameters.size() ; i++) {

                    if (editedParameters.size() < i) {
                        editedParametersHash.put(parameters.get(i), editedParameters.get(i));
                    } else {
                        editedParametersHash.put(parameters.get(i), "");
                    }
                }

                Method sendMethod = new Method();

                sendMethod.setAmount(method.getAmount());
                sendMethod.setParameters(editedParametersHash);

                User sendUser = new User();
                sendUser.setSsId(CurrentUserCredentials.getInstance().getId());

                Userinteractor.userPay(sendUser, sendMethod).enqueue(
                        new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {


                                finish();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        }
                );




        }
    }
}
