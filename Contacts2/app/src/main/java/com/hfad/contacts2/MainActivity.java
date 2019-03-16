package com.hfad.contacts2;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Uri uri;
    String name;
    ArrayList<Data> data;
    RecyclerView mRecyclerView;

    ListAdapterActivity mListAdapterActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.recview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListAdapterActivity = new ListAdapterActivity(data,this);
        mRecyclerView.setAdapter(mListAdapterActivity);

    }

    class Data {

        Uri uri;
        String name;

        public void setNumber(String number) {

            this.number = number;

        }

        String number;

        public Uri getUri() {

            return uri;

        }

        public String getName() {

            return name;

        }

        public String getNumber() {

            return number;

        }

        public void setUri(Uri uri) {

            this.uri = uri;

        }

        public void setName(String name) {

            this.name = name;

        }


    }

}
