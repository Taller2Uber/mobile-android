package com.example.slazzari.taller2uber.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.slazzari.taller2uber.R;
import com.example.slazzari.taller2uber.activity.Register.MyAdapter;
import com.example.slazzari.taller2uber.model.Car;
import com.example.slazzari.taller2uber.model.CurrentUserCredentials;
import com.example.slazzari.taller2uber.model.Payment.Method;
import com.example.slazzari.taller2uber.model.Payment.Methods;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.example.slazzari.taller2uber.networking.repository.Userrepo;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    private RecyclerView paymentMethodsRecyclerView;
    private LinearLayoutManager paymentLayoutManager;
    private PaymentAdapter paymentAdapter;

    private Methods methods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        paymentMethodsRecyclerView = (RecyclerView)findViewById(R.id.payment_methods_recycler_view);
        paymentLayoutManager = new LinearLayoutManager(this);
        paymentMethodsRecyclerView.setLayoutManager(paymentLayoutManager);

        User debtUser = new User();
        debtUser.setSsId(CurrentUserCredentials.getInstance().getId());

        paymentMethodsRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                paymentMethodsRecyclerView, new ClickListener() {

            @Override
            public void onClick(View view, final int position) {
                Intent intent = new Intent (PaymentActivity.this, PayActivity.class);

                intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK);

                Gson gson = new Gson();

                Method method = methods.getPaymethods().get(position);
                method.setAmmount(methods.getBalance());

                intent.putExtra("obj", gson.toJson(methods.getPaymethods().get(position)));

                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));


        Userinteractor.userDebt(debtUser).enqueue(
                new Callback<Methods>() {
                    @Override
                    public void onResponse(Call<Methods> call, Response<Methods> response) {
                        methods = response.body();
                        paymentAdapter = new PaymentAdapter(methods.getPaymethods());
                        paymentMethodsRecyclerView.setAdapter(paymentAdapter);
                    }

                    @Override
                    public void onFailure(Call<Methods> call, Throwable t) {

                    }
                }
        );
    }



















    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public static interface ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view,int position);
    }
}
