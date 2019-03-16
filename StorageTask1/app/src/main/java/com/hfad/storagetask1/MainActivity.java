package com.hfad.storagetask1;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.hfad.storagetask1.Model.AppDatabase;
import com.hfad.storagetask1.Model.Application;
import com.hfad.storagetask1.Model.Data;
import com.hfad.storagetask1.Model.DataDAO;
import com.hfad.storagetask1.Model.ExchangeValue;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView FirstTitle;
    TextView SecondTitle;
    TextView ThirdTitle;
    TextView FirstValue;
    TextView SecondValue;
    TextView ThirdValue;
    String value ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCustom);

        setSupportActionBar(toolbar);

        TextView textView = (TextView)toolbar.findViewById(R.id.toolBarText);
        textView.setText("USD");

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        value = "USD";

        FirstTitle= findViewById(R.id.FirstTitle);
        SecondTitle= findViewById(R.id.SecondTitle);
        ThirdTitle= findViewById(R.id.ThirdTitle);

        FirstValue = findViewById(R.id.FirstValue);
        SecondValue = findViewById(R.id.SecondValue);
        ThirdValue = findViewById(R.id.ThirdValue);

        Application.sApiHelper.getExchange(value).enqueue(new Callback<ExchangeValue>() {
            @Override
            public void onResponse(Call<ExchangeValue> call, Response<ExchangeValue> response) {

                FirstTitle.setText("RUB");
                FirstValue.setText(response.body().getRates().getRUB().toString());
                SecondTitle.setText("GBP");
                SecondValue.setText(response.body().getRates().getGBP().toString());
                ThirdTitle.setText("EUR");
                ThirdValue.setText(response.body().getRates().getEUR().toString());

                Log.d("response", "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<ExchangeValue> call, Throwable t) {
                Log.d("resp", t.toString());
            }
        });
    }

    AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "db-contacts")
            .allowMainThreadQueries()   //Allows room to do operation on main thread
            .build();

    DataDAO dataDAO = database.getDataDAO();

    //Inserting a contact
    Data data = new Data(value);

    dataDAO.insert(data);

    //Fetching all contacts
    List<Data> contact = dataDAO.getValue();

    //Getting a single contact and updating it
    dataDAO.update(contact);
}
