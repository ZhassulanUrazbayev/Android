package com.hfad.contacts3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Add extends AppCompatActivity {

    ImageView imageView;
    Button ButtonSave;
    Button ButtonCancel;

    EditText name;
    EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        imageView = findViewById(R.id.imageView);

        name = findViewById(R.id.Name);
        number = findViewById(R.id.Number);

        ButtonSave = findViewById(R.id.ButtonSave);
        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Add.this, MainActivity.class);

                Data data = new Data(name.getText().toString(),number.getText().toString());
                if (!data.getName().equals("")  && !data.getNumber().equals("" +
                        "")){
                ArrayList list = new ArrayList();
                list = getArrayList("data");
                list.add(data);
                saveArrayList(list,"data");
                startActivity(i);
                finish();
                }
            }
        });
        ButtonCancel = findViewById(R.id.ButtonCancel);
        ButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Add.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }


    public void saveArrayList(ArrayList<Data> list, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
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
        else {
            return gson.fromJson(json, type);
        }
    }
}
