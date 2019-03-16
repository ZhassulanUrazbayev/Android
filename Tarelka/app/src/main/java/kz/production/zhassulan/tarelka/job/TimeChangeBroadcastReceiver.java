package kz.production.kuanysh.tarelka.job;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.evernote.android.job.JobManager;

import kz.production.kuanysh.tarelka.R;
import kz.production.kuanysh.tarelka.ui.activities.mainactivity.MainActivity;
import kz.production.kuanysh.tarelka.utils.AppConstants;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

/**
 * Created by User on 07.09.2018.
 */

public class TimeChangeBroadcastReceiver extends BroadcastReceiver{

    private static final String CHANNEL_ID = "adminMessageChannelNotBackground";
    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (action.equals(Intent.ACTION_TIME_CHANGED) ||
                action.equals(Intent.ACTION_TIMEZONE_CHANGED)) {

            Log.d("CLOCK", "active jobs count: "+JobManager.instance().getAllJobRequests().size());

            JobManager.instance().cancelAllForTag(EveningNotification.TAG);
            JobManager.instance().cancelAllForTag(MorningNotification.TAG);
            JobManager.instance().cancelAllForTag(AfternoonNotification.TAG);

            Log.d("CLOCK", "active jobs count: "+JobManager.instance().getAllJobRequests().size());
            MorningNotification.schedule();
            AfternoonNotification.schedule();
            EveningNotification.schedule();

            Log.d("CLOCK", "after resheduling active jobs count: "+JobManager.instance().getAllJobRequests().size());




        }
    }
}
