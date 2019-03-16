package com.hfad.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.CustomToolBar);

        setSupportActionBar(toolbar);

        TextView Title = (TextView)toolbar.findViewById(R.id.ToolBarTitle);
        TextView NumberOfUsers = toolbar.findViewById(R.id.ToolBarNumberOfUsers);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }
}
