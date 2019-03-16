package com.hfad.servicestaskactivity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    Button mButton;
    BroadcastReceiver mBroadcastReceiver;

    public final static int STATUS_START = 100;
    public final static int STATUS_END = 200;

    public final static String PARAM_STATUS = "status";

    public final static String BROADCAST_ACTION = "com.hfad.servicebroadcast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.text);
        mButton = findViewById(R.id.button);
        mBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {

                int status = intent.getIntExtra(PARAM_STATUS, 0);

                if (status == STATUS_START) {

                    mTextView.setText("Start");

                }

                if (status == STATUS_END) {

                    mTextView.setText("End");

                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(mBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    public void onClick(View view) {

        mTextView .setText("Running");

        Intent intent = new Intent(this,MyServices.class);

        startService(intent);
    }
}
