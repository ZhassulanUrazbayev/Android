package com.hfad.urazbayevzhassulanfirstmidtermtask;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Button mButton;
    String massage;
    CustomAdapter mCustomAdapter;
    Data data;
    TimePicker mTimePicker;
    EditText mEditText;
    Button gone;
    ArrayList<Data> mData = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mTimePicker = findViewById(R.id.TimePicker);
        mEditText = findViewById(R.id.Text);
        gone = findViewById(R.id.AddButton);
        mRecyclerView = findViewById(R.id.RecyclerView);

        data = new Data(mEditText.getText().toString(),mTimePicker.getHour()+"",mTimePicker.getMinute()+"");
        data.setHour(mTimePicker.getHour()+"");
        data.setMinute(mTimePicker.getMinute()+"");
        data.setDescription(mEditText.getText().toString());

        mData.add(data);

        if(mData!=null) {
            addData(mData);
        }
    }

    private void addData(ArrayList<Data> data){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCustomAdapter= new CustomAdapter(data,this);
        mRecyclerView.setAdapter(mCustomAdapter);
    }

}
