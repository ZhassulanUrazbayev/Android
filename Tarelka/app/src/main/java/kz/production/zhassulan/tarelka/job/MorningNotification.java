package kz.production.kuanysh.tarelka.job;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.evernote.android.job.DailyJob;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import kz.production.kuanysh.tarelka.R;
import kz.production.kuanysh.tarelka.ui.activities.mainactivity.MainActivity;
import kz.production.kuanysh.tarelka.utils.AppConstants;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

/**
 * Created by User on 12.08.2018.
 */

public class MorningNotification extends DailyJob {

    public static final String TAG = "MyDailyJobMorn";
    private static final String CHANNEL_ID = "notMorning";


    public static void schedule() {
        DailyJob.schedule(new JobRequest.Builder(TAG), TimeUnit.HOURS.toMillis(8)+
                TimeUnit.MINUTES.toMillis(30), TimeUnit.HOURS.toMillis(8)+
                TimeUnit.MINUTES.toMillis(10
                ));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    protected DailyJob.DailyJobResult onRunDailyJob(Job.Params params) {

        Intent notificationIntent = new Intent(getContext(), MainActivity.class);

        notificationIntent.putExtra(AppConstants.PUSH_ACTION, AppConstants.PUSH_ACTION);

        PendingIntent pi = PendingIntent.getActivity(getContext(), 0,
                notificationIntent, 0);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification.Builder builder = new Notification.Builder(getContext());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int color = 0x008000;
            builder.setColor(getContext().getResources().getColor(R.color.blueNotification));
        }

        Notification notification = builder
                .setContentTitle("Пора завтракать!")
                .setTicker("Новое напоминание от Tarelka!")
                .setContentText("Не забудь отправить фото")
                .setSound(defaultSoundUri)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setShowWhen(true)
                .setSmallIcon(R.mipmap.ic_noticon).build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("OREO", "morning local notification");
            builder.setChannelId(CHANNEL_ID);
        }

        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "NotificationDemo",
                    IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(99, notification);


        return DailyJob.DailyJobResult.SUCCESS;
    }
}