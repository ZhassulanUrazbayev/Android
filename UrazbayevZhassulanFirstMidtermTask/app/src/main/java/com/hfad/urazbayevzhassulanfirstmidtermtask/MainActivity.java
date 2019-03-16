package com.hfad.urazbayevzhassulanfirstmidtermtask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Button mButton;
    String massage;
    CustomAdapter mCustomAdapter;
    ArrayList<Data> ata = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.AddButton);
        mRecyclerView = findViewById(R.id.RecyclerView);

        Data data = new Data("Good Morning","08","00");
        data.setHour("08");
        data.setMinute("00");
        data.setDescription("Good Morning");
        ata.add(data);
        Data data1 = new Data("Play Football","12","30");
        data1.setHour("12");
        data1.setMinute("30");
        data1.setDescription("Play Football");
        ata.add(data1);
        Data data2 = new Data("Cinema","16","45");
        data2.setHour("16");
        data2.setMinute("45");
        data2.setDescription("Cinema");
        ata.add(data2);
        Data data3 = new Data("Sleep","22","59");
        data3.setHour("22");
        data3.setMinute("59");
        data3.setDescription("Sleep");
        ata.add(data3);

        if(ata!=null) {
            addData(ata);
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addData(ArrayList<Data> data){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCustomAdapter= new CustomAdapter(data,this);
        mRecyclerView.setAdapter(mCustomAdapter);
    }
}
