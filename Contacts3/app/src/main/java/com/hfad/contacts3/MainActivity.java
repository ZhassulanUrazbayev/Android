package com.hfad.contacts3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    Button addBtn;

    ListAdapter mListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "onCreate: ");

        addBtn = findViewById(R.id.addBtn);
        mRecyclerView = (RecyclerView)findViewById(R.id.recview);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Add.class);
                startActivity(intent);
            }
        });

        ArrayList<Data> data = getArrayList("data");

        if(data!=null) {
            addData(data);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart: ");
    }

    private void addData(ArrayList<Data> data){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListAdapter= new ListAdapter(data,this);
        mRecyclerView.setAdapter(mListAdapter);
    }

    public ArrayList<Data> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Data>>() {}.getType();

        if(gson.fromJson(json, type) == null){
            ArrayList list = new ArrayList<>();
            return list;
        }
        else{
            return gson.fromJson(json, type);
        }

    }

}
