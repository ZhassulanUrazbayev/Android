package com.hfad.firstmidproject.Model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toolbar;

import com.hfad.firstmidproject.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExchangeRatio extends AppCompatActivity {
    TextView FirstTitle;
    TextView SecondTitle;
    TextView ThirdTitle;
    TextView FirstValue;
    TextView SecondValue;
    TextView ThirdValue;
    String value ;
    Toolbar toolbarTop;
    TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_ratio);
        value = getIntent().getStringExtra("value");
        Log.d("value", value);

        FirstTitle= findViewById(R.id.FirstTitle);
        SecondTitle= findViewById(R.id.SecondTitle);
        ThirdTitle= findViewById(R.id.ThirdTitle);

        FirstValue = findViewById(R.id.FirstValue);
        SecondValue = findViewById(R.id.SecondValue);
        ThirdValue = findViewById(R.id.ThirdValue);

        Application.sApiHelper.getExchange(value).enqueue(new Callback<ExchangeValue>() {
            @Override
            public void onResponse(Call<ExchangeValue> call, Response<ExchangeValue> response) {

                switch (value){
                    case "EUR":
                        FirstTitle.setText("RUB");
                        FirstValue.setText(response.body().getRates().getRUB().toString());
                        SecondTitle.setText("GBP");
                        SecondValue.setText(response.body().getRates().getGBP().toString());
                        ThirdTitle.setText("USD");
                        ThirdValue.setText(response.body().getRates().getUSD().toString());
                        break;
                    case "USD":
                        FirstTitle.setText("RUB");
                        FirstValue.setText(response.body().getRates().getRUB().toString());
                        SecondTitle.setText("GBP");
                        SecondValue.setText(response.body().getRates().getGBP().toString());
                        ThirdTitle.setText("EUR");
                        ThirdValue.setText(response.body().getRates().getEUR().toString());
                        break;
                    case "GBP":
                        FirstTitle.setText("RUB");
                        FirstValue.setText(response.body().getRates().getRUB().toString());
                        SecondTitle.setText("EUR");
                        SecondValue.setText(response.body().getRates().getEUR().toString());
                        ThirdTitle.setText("USD");
                        ThirdValue.setText(response.body().getRates().getUSD().toString());
                        break;
                    case "RUB":
                        FirstTitle.setText("EUR");
                        FirstValue.setText(response.body().getRates().getEUR().toString());
                        SecondTitle.setText("GBP");
                        SecondValue.setText(response.body().getRates().getGBP().toString());
                        ThirdTitle.setText("USD");
                        ThirdValue.setText(response.body().getRates().getUSD().toString());
                        break;
                }
                Log.d("response", "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<ExchangeValue> call, Throwable t) {
                Log.d("resp", t.toString());
            }

        });
    }
}
