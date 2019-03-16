package com.hfad.firstmidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.hfad.firstmidproject.Model.ExchangeRatio;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView usd;
    TextView eur;
    TextView gbp;
    TextView rub;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCustom);

        setSupportActionBar(toolbar);

        TextView textView = (TextView)toolbar.findViewById(R.id.toolBarText);
        textView.setText("String");

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        usd = findViewById(R.id.USD);
        eur = findViewById(R.id.EUR);
        gbp = findViewById(R.id.GBP);
        rub = findViewById(R.id.RUB);

        final Data dollar = new Data("USD");
        final Data euro = new Data("EUR");
        final Data fund = new Data("GBP");
        final Data ruble = new Data("RUB");

        usd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ExchangeRatio.class);

                intent.putExtra("value",dollar.getValue());
                startActivity(intent);
            }
        });

        eur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ExchangeRatio.class);

                intent.putExtra("value",euro.getValue());
                startActivity(intent);
            }
        });

        gbp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ExchangeRatio.class);

                intent.putExtra("value",fund.getValue());
                startActivity(intent);
            }
        });

        rub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ExchangeRatio.class);

                intent.putExtra("value",ruble.getValue());
                startActivity(intent);
            }
        });




    }
}
