package com.hfad.labrecycler;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    ArrayList<Uri> uri;
    ArrayList<String> name;
    ArrayList<Long> lon;
    RecyclerView mRecyclerView;

    ListAdapter mListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.Rec);

        Data data = new Data();
        Data data1 = new Data();
        Data data2 = new Data();
        Data data3 = new Data();
        Data data4 = new Data();
        Data data5 = new Data();

        ArrayList<Data> inf = new ArrayList<>();

        data.setName("Kostya");
        data.setAge((long) 74);
        data.setUri(null);
        inf.add(data);

        data1.setName("Vanya");
        data1.setAge((long) 54);
        data1.setUri(null);
        inf.add(data1);

        data2.setName("Erbol");
        data2.setAge((long) 18);
        data2.setUri(null);
        inf.add(data2);

        data3.setName("Aidos");
        data3.setAge((long) 98);
        data3.setUri(null);
        inf.add(data3);

        data4.setName("Maks");
        data4.setAge((long) 4);
        data4.setUri(null);
        inf.add(data4);

        data5.setName("Askar");
        data5.setAge((long) 14);
        data5.setUri(null);
        inf.add(data5);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListAdapter= new ListAdapter( inf,this);
        mRecyclerView.setAdapter(mListAdapter);
        mListAdapter.notifyDataSetChanged();
    }
}

    class Data{

        Uri uri;
        String name;
        Long lon;

        public Uri getUri(){

            return uri;

        }
        public String getName(){

            return name;

        }

        public Long getAge(){

            return lon;

        }

        public void  setUri(Uri uri){

            this.uri=uri;

        }
        public void setName(String name){

            this.name=name;

        }

        public void setAge(Long lon){

            this.lon = lon;

        }
    }


