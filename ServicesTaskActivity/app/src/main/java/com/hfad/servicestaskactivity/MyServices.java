package com.hfad.servicestaskactivity;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import java.util.concurrent.TimeUnit;

public class MyServices extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent MainIntent = new Intent(MainActivity.BROADCAST_ACTION);

        try{

            TimeUnit.SECONDS.sleep(4);

            MainIntent.putExtra(MainActivity.PARAM_STATUS,MainActivity.STATUS_END);

            sendBroadcast(MainIntent);

            TimeUnit.SECONDS.sleep(2);

            MainIntent.putExtra(MainActivity.PARAM_STATUS,MainActivity.STATUS_START);

            sendBroadcast(MainIntent);

        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
